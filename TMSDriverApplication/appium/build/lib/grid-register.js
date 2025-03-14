"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const axios_1 = __importDefault(require("axios"));
const support_1 = require("@appium/support");
const logger_1 = __importDefault(require("./logger"));
const lodash_1 = __importDefault(require("lodash"));
const hubUri = (config) => {
    const protocol = config.hubProtocol || 'http';
    return `${protocol}://${config.hubHost}:${config.hubPort}`;
};
/**
 * Registers a new node with a selenium grid
 * @param {string|object} data - Path or object representing selenium grid node config file. If a `string`, all subsequent arguments are required!
 * @param {string} [addr] - Bind to this address
 * @param {number} [port] - Bind to this port
 * @param {string} [basePath] - Base path for the grid
 */
async function registerNode(data, addr, port, basePath) {
    let configFilePath;
    if (lodash_1.default.isString(data)) {
        configFilePath = data;
        try {
            data = await support_1.fs.readFile(data, 'utf-8');
        }
        catch (err) {
            logger_1.default.error(`Unable to load node configuration file ${configFilePath} to register with grid: ${err.message}`);
            return;
        }
        try {
            data = JSON.parse(data);
        }
        catch (err) {
            throw logger_1.default.errorWithException(`Syntax error in node configuration file ${configFilePath}: ${err.message}`);
        }
    }
    postRequest(data, addr, port, basePath);
}
async function registerToGrid(postOptions, configHolder) {
    try {
        const { status } = await (0, axios_1.default)(postOptions);
        if (status !== 200) {
            throw new Error(`Request failed with code ${status}`);
        }
        logger_1.default.debug(`Appium successfully registered with the the grid on ` + hubUri(configHolder.configuration));
    }
    catch (err) {
        logger_1.default.error(`An attempt to register with the grid was unsuccessful: ${err.message}`);
    }
}
function postRequest(configHolder, addr, port, basePath) {
    // Move Selenium 3 configuration properties to configuration object
    if (!lodash_1.default.has(configHolder, 'configuration')) {
        let configuration = {};
        for (const property in /** @type {import('@appium/types').StringRecord} */ (configHolder)) {
            if (lodash_1.default.has(configHolder, property) && property !== 'capabilities') {
                configuration[property] = configHolder[property];
                delete configHolder[property];
            }
        }
        /** @type {import('@appium/types').StringRecord} */ (configHolder).configuration = configuration;
    }
    // if the node config does not have the appium/webdriver url, host, and port,
    // automatically add it based on how appium was initialized
    // otherwise, we will take whatever the user setup
    // because we will always set localhost/127.0.0.1. this won't work if your
    // node and grid aren't in the same place
    if (!configHolder.configuration.url ||
        !configHolder.configuration.host ||
        !configHolder.configuration.port) {
        configHolder.configuration.url = `http://${addr}:${port}${basePath}`;
        configHolder.configuration.host = addr;
        configHolder.configuration.port = port;
    }
    // if the node config does not have id automatically add it
    if (!configHolder.configuration.id) {
        configHolder.configuration.id = `http://${configHolder.configuration.host}:${configHolder.configuration.port}`;
    }
    // the post options
    const regRequest = {
        url: `${hubUri(configHolder.configuration)}/grid/register`,
        method: 'POST',
        data: configHolder,
    };
    if (configHolder.configuration.register !== true) {
        logger_1.default.debug(`No registration sent (${configHolder.configuration.register} = false)`);
        return;
    }
    const registerCycleInterval = configHolder.configuration.registerCycle;
    if (isNaN(registerCycleInterval) || registerCycleInterval <= 0) {
        logger_1.default.warn(`'registerCycle' is not a valid positive number. ` +
            `No registration request will be sent to the grid.`);
        return;
    }
    // initiate a new Thread
    let first = true;
    logger_1.default.debug(`Starting auto register thread for the grid. ` +
        `Will try to register every ${registerCycleInterval} ms.`);
    setInterval(async function registerRetry() {
        if (first) {
            first = false;
            await registerToGrid(regRequest, configHolder);
        }
        else if (!(await isAlreadyRegistered(configHolder))) {
            // make the http POST to the grid for registration
            await registerToGrid(regRequest, configHolder);
        }
    }, registerCycleInterval);
}
async function isAlreadyRegistered(configHolder) {
    //check if node is already registered
    const id = configHolder.configuration.id;
    try {
        const { data, status } = await (0, axios_1.default)({
            url: `${hubUri(configHolder.configuration)}/grid/api/proxy?id=${id}`,
            timeout: 10000,
        });
        if (status !== 200) {
            throw new Error(`Request failed with code ${status}`);
        }
        if (!data.success) {
            // if register fail, print the debug msg
            logger_1.default.debug(`Grid registration error: ${data.msg}`);
        }
        return data.success;
    }
    catch (err) {
        logger_1.default.debug(`Hub down or not responding: ${err.message}`);
    }
}
exports.default = registerNode;
//# sourceMappingURL=grid-register.js.map