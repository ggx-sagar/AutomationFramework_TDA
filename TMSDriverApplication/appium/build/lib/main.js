#!/usr/bin/env node
"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.resolveAppiumHome = exports.validate = exports.getSchema = exports.finalizeSchema = exports.readConfigFile = void 0;
exports.main = main;
exports.init = init;
const ws_1 = require("ws");
const logsink_1 = require("./logsink"); // this import needs to come first since it sets up global npmlog
const logger_1 = __importDefault(require("./logger")); // logger needs to remain second
const base_driver_1 = require("@appium/base-driver");
const support_1 = require("@appium/support");
const asyncbox_1 = require("asyncbox");
const lodash_1 = __importDefault(require("lodash"));
const appium_1 = require("./appium");
const extension_1 = require("./cli/extension");
const setup_command_1 = require("./cli/setup-command");
const parser_1 = require("./cli/parser");
const config_1 = require("./config");
const config_file_1 = require("./config-file");
const extension_2 = require("./extension");
const constants_1 = require("./constants");
const grid_register_1 = __importDefault(require("./grid-register"));
const schema_1 = require("./schema/schema");
const utils_1 = require("./utils");
const node_net_1 = __importDefault(require("node:net"));
const { resolveAppiumHome } = support_1.env;
exports.resolveAppiumHome = resolveAppiumHome;
/*
 * By default Node.js shows a warning
 * if the actual amount of listeners exceeds the maximum amount,
 * which equals to 10 by default. It is known that multiple drivers/plugins
 * may assign custom listeners to the server process to handle, for example,
 * the graceful shutdown scenario.
 */
const MAX_SERVER_PROCESS_LISTENERS = 100;
/**
 *
 * @param {ParsedArgs} args
 * @param {boolean} [throwInsteadOfExit]
 */
async function preflightChecks(args, throwInsteadOfExit = false) {
    try {
        (0, config_1.checkNodeOk)();
        if (args.longStacktrace) {
            Error.stackTraceLimit = constants_1.LONG_STACKTRACE_LIMIT;
        }
        if (args.showBuildInfo) {
            await (0, config_1.showBuildInfo)();
            process.exit(0);
        }
        (0, schema_1.validate)(args);
        if (args.tmpDir) {
            await (0, config_1.requireDir)(args.tmpDir, !args.noPermsCheck, 'tmpDir argument value');
        }
    }
    catch (err) {
        logger_1.default.error(err.message.red);
        if (throwInsteadOfExit) {
            throw err;
        }
        process.exit(1);
    }
}
/**
 * @param {Args} args
 */
function logNonDefaultArgsWarning(args) {
    logger_1.default.info('Non-default server args:');
    (0, utils_1.inspect)(args);
}
/**
 * @param {Args['defaultCapabilities']} caps
 */
function logDefaultCapabilitiesWarning(caps) {
    logger_1.default.info('Default capabilities, which will be added to each request ' +
        'unless overridden by desired capabilities:');
    (0, utils_1.inspect)(caps);
}
/**
 * @param {ParsedArgs} args
 */
async function logStartupInfo(args) {
    let welcome = `Welcome to Appium v${config_1.APPIUM_VER}`;
    let appiumRev = await (0, config_1.getGitRev)();
    if (appiumRev) {
        welcome += ` (REV ${appiumRev})`;
    }
    logger_1.default.info(welcome);
    let showArgs = (0, config_1.getNonDefaultServerArgs)(args);
    if (lodash_1.default.size(showArgs)) {
        logNonDefaultArgsWarning(showArgs);
    }
    if (!lodash_1.default.isEmpty(args.defaultCapabilities)) {
        logDefaultCapabilitiesWarning(args.defaultCapabilities);
    }
    // TODO: bring back loglevel reporting below once logger is flushed out
    // logger.info('Console LogLevel: ' + logger.transports.console.level);
    // if (logger.transports.file) {
    //   logger.info('File LogLevel: ' + logger.transports.file.level);
    // }
}
/**
 * Gets a list of `updateServer` functions from all extensions
 * @param {DriverNameMap} driverClasses
 * @param {PluginNameMap} pluginClasses
 * @returns {import('@appium/types').UpdateServerCallback[]}
 */
function getServerUpdaters(driverClasses, pluginClasses) {
    return lodash_1.default.compact(lodash_1.default.map([...driverClasses.keys(), ...pluginClasses.keys()], 'updateServer'));
}
/**
 * Makes a big `MethodMap` from all the little `MethodMap`s in the extensions
 * @param {DriverNameMap} driverClasses
 * @param {PluginNameMap} pluginClasses
 * @returns {import('@appium/types').MethodMap<import('@appium/types').Driver>}
 */
function getExtraMethodMap(driverClasses, pluginClasses) {
    return [...driverClasses.keys(), ...pluginClasses.keys()].reduce((map, klass) => ({
        ...map,
        ...(klass.newMethodMap ?? {}),
    }), {});
}
/**
 * @param {string?} [appiumHomeFromArgs] - Appium home value retrieved from progrmmatic server args
 * @returns {string}
 */
function determineAppiumHomeSource(appiumHomeFromArgs) {
    if (!lodash_1.default.isNil(appiumHomeFromArgs)) {
        return 'appiumHome config value';
    }
    else if (process.env.APPIUM_HOME) {
        return 'APPIUM_HOME environment variable';
    }
    return 'autodetected Appium home path';
}
/**
 * Initializes Appium, but does not start the server.
 *
 * Use this to get at the configuration schema.
 *
 * If `args` contains a non-empty `subcommand` which is not `server`, this function will return an empty object.
 *
 * @template {CliCommand} [Cmd=ServerCommand]
 * @template {CliExtensionSubcommand|void} [SubCmd=void]
 * @param {Args<Cmd, SubCmd>} [args] - Partial args (progammatic usage only)
 * @returns {Promise<InitResult<Cmd>>}
 * @example
 * import {init, getSchema} from 'appium';
 * const options = {}; // config object
 * await init(options);
 * const schema = getSchema(); // entire config schema including plugins and drivers
 */
async function init(args) {
    const appiumHome = args?.appiumHome ?? (await resolveAppiumHome());
    const appiumHomeSourceName = determineAppiumHomeSource(args?.appiumHome);
    // We verify the writeability later based on requested server arguments
    // Here we just need to make sure the path exists and is a folder
    await (0, config_1.requireDir)(appiumHome, false, appiumHomeSourceName);
    (0, utils_1.adjustNodePath)();
    const { driverConfig, pluginConfig } = await (0, extension_2.loadExtensions)(appiumHome);
    const parser = (0, parser_1.getParser)();
    let throwInsteadOfExit = false;
    /** @type {Args<Cmd, SubCmd>} */
    let preConfigArgs;
    if (args) {
        // if we have a containing package instead of running as a CLI process,
        // that package might not appreciate us calling 'process.exit' willy-
        // nilly, so give it the option to have us throw instead of exit
        if (args.throwInsteadOfExit) {
            throwInsteadOfExit = true;
            // but remove it since it's not a real server arg per se
            delete args.throwInsteadOfExit;
        }
        preConfigArgs = { ...args, subcommand: args.subcommand ?? constants_1.SERVER_SUBCOMMAND };
    }
    else {
        // otherwise parse from CLI
        preConfigArgs = /** @type {Args<Cmd, SubCmd>} */ (parser.parseArgs());
    }
    const configResult = await (0, config_file_1.readConfigFile)(preConfigArgs.configFile);
    if (!lodash_1.default.isEmpty(configResult.errors)) {
        throw new Error(`Errors in config file ${configResult.filepath}:\n ${configResult.reason ?? configResult.errors}`);
    }
    // merge config and apply defaults.
    // the order of precedence is:
    // 1. command line args
    // 2. config file
    // 3. defaults from config file.
    if ((0, utils_1.isServerCommandArgs)(preConfigArgs)) {
        const defaults = (0, schema_1.getDefaultsForSchema)(false);
        /** @type {ParsedArgs} */
        const serverArgs = lodash_1.default.defaultsDeep({}, preConfigArgs, configResult.config?.server, defaults);
        if (preConfigArgs.showConfig) {
            (0, config_1.showConfig)((0, config_1.getNonDefaultServerArgs)(preConfigArgs), configResult, defaults, serverArgs);
            return /** @type {InitResult<Cmd>} */ ({});
        }
        if (preConfigArgs.showDebugInfo) {
            await (0, config_1.showDebugInfo)({
                driverConfig,
                pluginConfig,
                appiumHome,
            });
            return /** @type {InitResult<Cmd>} */ ({});
        }
        await (0, logsink_1.init)(serverArgs);
        if (serverArgs.logFilters) {
            const { issues, rules } = await logger_1.default.unwrap().loadSecureValuesPreprocessingRules(serverArgs.logFilters);
            const argToLog = lodash_1.default.truncate(JSON.stringify(serverArgs.logFilters), {
                length: 150
            });
            if (!lodash_1.default.isEmpty(issues)) {
                throw new Error(`The log filtering rules config ${argToLog} has issues: ` +
                    JSON.stringify(issues, null, 2));
            }
            if (lodash_1.default.isEmpty(rules)) {
                logger_1.default.warn(`Found no log filtering rules in the ${argToLog} config. ` +
                    `Is that expected?`);
            }
            else {
                // Filtering aims to "hide" these values from the log,
                // so it would be nice to not show them in the log as well.
                logger_1.default.info(`Loaded ${support_1.util.pluralize('filtering rule', rules.length, true)}`);
            }
        }
        if (!serverArgs.noPermsCheck) {
            await (0, config_1.requireDir)(appiumHome, true, appiumHomeSourceName);
        }
        const appiumDriver = new appium_1.AppiumDriver(
        /** @type {import('@appium/types').DriverOpts<import('./appium').AppiumDriverConstraints>} */ (serverArgs));
        // set the config on the umbrella driver so it can match drivers to caps
        appiumDriver.driverConfig = driverConfig;
        await preflightChecks(serverArgs, throwInsteadOfExit);
        return /** @type {InitResult<Cmd>} */ ({
            appiumDriver,
            parsedArgs: serverArgs,
            driverConfig,
            pluginConfig,
            appiumHome,
        });
    }
    else if ((0, utils_1.isSetupCommandArgs)(preConfigArgs)) {
        await (0, setup_command_1.runSetupCommand)(preConfigArgs, driverConfig, pluginConfig);
        return /** @type {InitResult<Cmd>} */ ({});
    }
    else {
        await (0, config_1.requireDir)(appiumHome, true, appiumHomeSourceName);
        if ((0, utils_1.isExtensionCommandArgs)(preConfigArgs)) {
            // if the user has requested the 'driver' CLI, don't run the normal server,
            // but instead pass control to the driver CLI
            if ((0, utils_1.isDriverCommandArgs)(preConfigArgs)) {
                await (0, extension_1.runExtensionCommand)(preConfigArgs, driverConfig);
            }
            if ((0, utils_1.isPluginCommandArgs)(preConfigArgs)) {
                await (0, extension_1.runExtensionCommand)(preConfigArgs, pluginConfig);
            }
        }
        return /** @type {InitResult<Cmd>} */ ({});
    }
}
/**
 * Prints the actual server address and the list of URLs that
 * could be used to connect to the current server.
 * Properly replaces broadcast addresses in client URLs.
 *
 * @param {string} url The URL the server is listening on
 */
function logServerAddress(url) {
    const urlObj = new URL(url);
    logger_1.default.info(`Appium REST http interface listener started on ${url}`);
    if (!(0, utils_1.isBroadcastIp)(urlObj.hostname)) {
        return;
    }
    const interfaces = (0, utils_1.fetchInterfaces)(urlObj.hostname === utils_1.V4_BROADCAST_IP ? 4 : 6);
    const toLabel = (/** @type {import('node:os').NetworkInterfaceInfo} */ iface) => {
        const href = urlObj.href.replace(urlObj.hostname, iface.address);
        return iface.internal ? `${href} (only accessible from the same host)` : href;
    };
    logger_1.default.info(`You can provide the following ${interfaces.length === 1 ? 'URL' : 'URLs'} ` +
        `in your client code to connect to this server:\n` +
        interfaces.map((iface) => `\t${toLabel(iface)}`).join('\n'));
}
/**
 * Initializes Appium's config.  Starts server if appropriate and resolves the
 * server instance if so; otherwise resolves w/ `undefined`.
 * @template {CliCommand} [Cmd=ServerCommand]
 * @template {CliExtensionSubcommand|void} [SubCmd=void]
 * @param {Args<Cmd, SubCmd>} [args] - Arguments from CLI or otherwise
 * @returns {Promise<Cmd extends ServerCommand ? import('@appium/types').AppiumServer : void>}
 */
async function main(args) {
    const initResult = await init(args);
    if (lodash_1.default.isEmpty(initResult)) {
        // if this branch is taken, we've run a different subcommand, so there's nothing
        // left to do here.
        return /** @type {Cmd extends ServerCommand ? import('@appium/types').AppiumServer : void} */ (undefined);
    }
    const { appiumDriver, pluginConfig, driverConfig, parsedArgs, appiumHome } = 
    /** @type {InitResult<ServerCommand>} */ (initResult);
    const pluginClasses = await (0, extension_2.getActivePlugins)(pluginConfig, parsedArgs.pluginsImportChunkSize, parsedArgs.usePlugins);
    // set the active plugins on the umbrella driver so it can use them for commands
    appiumDriver.pluginClasses = pluginClasses;
    await logStartupInfo(parsedArgs);
    const appiumHomeSourceName = determineAppiumHomeSource(args?.appiumHome);
    logger_1.default.debug(`The ${appiumHomeSourceName}: ${appiumHome}`);
    let routeConfiguringFunction = (0, base_driver_1.routeConfiguringFunction)(appiumDriver);
    const driverClasses = await (0, extension_2.getActiveDrivers)(driverConfig, parsedArgs.driversImportChunkSize, parsedArgs.useDrivers);
    const serverUpdaters = getServerUpdaters(driverClasses, pluginClasses);
    const extraMethodMap = getExtraMethodMap(driverClasses, pluginClasses);
    /** @type {import('@appium/base-driver').ServerOpts} */
    const serverOpts = {
        routeConfiguringFunction,
        port: parsedArgs.port,
        hostname: parsedArgs.address,
        allowCors: parsedArgs.allowCors,
        basePath: parsedArgs.basePath,
        serverUpdaters,
        extraMethodMap,
        cliArgs: parsedArgs,
    };
    if (parsedArgs.keepAliveTimeout) {
        serverOpts.keepAliveTimeout = parsedArgs.keepAliveTimeout * 1000;
    }
    let server;
    const bidiServer = new ws_1.WebSocketServer({ noServer: true });
    bidiServer.on('connection', appiumDriver.onBidiConnection.bind(appiumDriver));
    bidiServer.on('error', appiumDriver.onBidiServerError.bind(appiumDriver));
    try {
        server = await (0, base_driver_1.server)(serverOpts);
        server.addWebSocketHandler('/bidi', bidiServer);
        server.addWebSocketHandler('/bidi/:sessionId', bidiServer);
    }
    catch (err) {
        logger_1.default.error(`Could not configure Appium server. It's possible that a driver or plugin tried ` +
            `to update the server and failed. Original error: ${err.message}`);
        logger_1.default.debug(err.stack);
        return process.exit(1);
    }
    if (parsedArgs.allowCors) {
        logger_1.default.warn('You have enabled CORS requests from any host. Be careful not ' +
            'to visit sites which could maliciously try to start Appium ' +
            'sessions on your machine');
    }
    appiumDriver.server = server;
    try {
        // configure as node on grid, if necessary
        // falsy values should not cause this to run
        if (parsedArgs.nodeconfig) {
            await (0, grid_register_1.default)(parsedArgs.nodeconfig, parsedArgs.address, parsedArgs.port, parsedArgs.basePath);
        }
    }
    catch (err) {
        await server.close();
        throw err;
    }
    process.setMaxListeners(MAX_SERVER_PROCESS_LISTENERS);
    for (const signal of ['SIGINT', 'SIGTERM']) {
        process.once(signal, async function onSignal() {
            logger_1.default.info(`Received ${signal} - shutting down`);
            try {
                await appiumDriver.shutdown(`The process has received ${signal} signal`);
                await server.close();
                process.exit(0);
            }
            catch (e) {
                logger_1.default.warn(e);
                process.exit(1);
            }
        });
    }
    const protocol = server.isSecure() ? 'https' : 'http';
    const address = node_net_1.default.isIPv6(parsedArgs.address) ? `[${parsedArgs.address}]` : parsedArgs.address;
    logServerAddress(`${protocol}://${address}:${parsedArgs.port}${(0, base_driver_1.normalizeBasePath)(parsedArgs.basePath)}`);
    driverConfig.print();
    pluginConfig.print([...pluginClasses.values()]);
    return /** @type {Cmd extends ServerCommand ? import('@appium/types').AppiumServer : void} */ (server);
}
// NOTE: this is here for backwards compat for any scripts referencing `main.js` directly
// (more specifically, `build/lib/main.js`)
// the executable is now `../index.js`, so that module will typically be `require.main`.
if (require.main === module) {
    (0, asyncbox_1.asyncify)(main);
}
// everything below here is intended to be a public API.
var config_file_2 = require("./config-file");
Object.defineProperty(exports, "readConfigFile", { enumerable: true, get: function () { return config_file_2.readConfigFile; } });
var schema_2 = require("./schema/schema");
Object.defineProperty(exports, "finalizeSchema", { enumerable: true, get: function () { return schema_2.finalizeSchema; } });
Object.defineProperty(exports, "getSchema", { enumerable: true, get: function () { return schema_2.getSchema; } });
Object.defineProperty(exports, "validate", { enumerable: true, get: function () { return schema_2.validate; } });
/**
 * @typedef {import('@appium/types').DriverType} DriverType
 * @typedef {import('@appium/types').PluginType} PluginType
 * @typedef {import('@appium/types').DriverClass} DriverClass
 * @typedef {import('@appium/types').PluginClass} PluginClass
 * @typedef {import('appium/types').CliCommand} CliCommand
 * @typedef {import('appium/types').CliExtensionSubcommand} CliExtensionSubcommand
 * @typedef {import('appium/types').CliExtensionCommand} CliExtensionCommand
 * @typedef {import('appium/types').CliCommandServer} ServerCommand
 * @typedef {import('appium/types').CliCommandDriver} DriverCommand
 * @typedef {import('appium/types').CliCommandPlugin} PluginCommand
 * @typedef {import('appium/types').CliCommandSetup} SetupCommand
 * @typedef {import('./extension').DriverNameMap} DriverNameMap
 * @typedef {import('./extension').PluginNameMap} PluginNameMap
 */
/**
 * Literally an empty object
 * @typedef { {} } ExtCommandInitResult
 */
/**
 * @typedef ServerInitData
 * @property {import('./appium').AppiumDriver} appiumDriver - The Appium driver
 * @property {import('appium/types').ParsedArgs} parsedArgs - The parsed arguments
 * @property {string} appiumHome - The full path to the Appium home folder
 */
/**
 * @template {CliCommand} Cmd
 * @typedef {Cmd extends ServerCommand ? ServerInitData & import('./extension').ExtensionConfigs : ExtCommandInitResult} InitResult
 */
/**
 * @template {CliCommand} [Cmd=ServerCommand]
 * @template {CliExtensionSubcommand|void} [SubCmd=void]
 * @typedef {import('appium/types').Args<Cmd, SubCmd>} Args
 */
/**
 * @template {CliCommand} [Cmd=ServerCommand]
 * @template {CliExtensionSubcommand|void} [SubCmd=void]
 * @typedef {import('appium/types').ParsedArgs<Cmd, SubCmd>} ParsedArgs
 */
//# sourceMappingURL=main.js.map