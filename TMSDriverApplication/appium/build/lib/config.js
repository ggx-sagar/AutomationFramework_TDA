"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || (function () {
    var ownKeys = function(o) {
        ownKeys = Object.getOwnPropertyNames || function (o) {
            var ar = [];
            for (var k in o) if (Object.prototype.hasOwnProperty.call(o, k)) ar[ar.length] = k;
            return ar;
        };
        return ownKeys(o);
    };
    return function (mod) {
        if (mod && mod.__esModule) return mod;
        var result = {};
        if (mod != null) for (var k = ownKeys(mod), i = 0; i < k.length; i++) if (k[i] !== "default") __createBinding(result, mod, k[i]);
        __setModuleDefault(result, mod);
        return result;
    };
})();
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.rootDir = exports.APPIUM_VER = void 0;
exports.updateBuildInfo = updateBuildInfo;
exports.showDebugInfo = showDebugInfo;
exports.getGitRev = getGitRev;
exports.getBuildInfo = getBuildInfo;
exports.checkNodeOk = checkNodeOk;
exports.showBuildInfo = showBuildInfo;
exports.getNonDefaultServerArgs = getNonDefaultServerArgs;
exports.showConfig = showConfig;
exports.requireDir = requireDir;
/* eslint-disable no-console */
const lodash_1 = __importDefault(require("lodash"));
const support_1 = require("@appium/support");
const axios_1 = __importDefault(require("axios"));
const teen_process_1 = require("teen_process");
const semver = __importStar(require("semver"));
const node_os_1 = __importDefault(require("node:os"));
const utils_1 = require("./utils");
const bluebird_1 = __importDefault(require("bluebird"));
const schema_1 = require("./schema/schema");
exports.APPIUM_VER = utils_1.npmPackage.version;
const ENGINES = /** @type {Record<string,string>} */ (utils_1.npmPackage.engines);
const MIN_NODE_VERSION = ENGINES.node;
exports.rootDir = support_1.fs.findRoot(__dirname);
const GIT_BINARY = `git${support_1.system.isWindows() ? '.exe' : ''}`;
const GITHUB_API = 'https://api.github.com/repos/appium/appium';
/**
 * @type {import('appium/types').BuildInfo}
 */
const BUILD_INFO = {
    version: exports.APPIUM_VER,
};
function getNodeVersion() {
    return /** @type {import('semver').SemVer} */ (semver.coerce(process.version));
}
/**
 * @param {boolean} [useGithubApiFallback]
 * @returns {Promise<void>}
 */
async function updateBuildInfo(useGithubApiFallback = false) {
    const sha = await getGitRev(useGithubApiFallback);
    if (!sha) {
        return;
    }
    BUILD_INFO['git-sha'] = sha;
    const buildTimestamp = await getGitTimestamp(sha, useGithubApiFallback);
    if (buildTimestamp) {
        BUILD_INFO.built = buildTimestamp;
    }
}
/** @type {() => Promise<string?>} */
const getFullGitPath = lodash_1.default.memoize(async function getFullGitPath() {
    try {
        return await support_1.fs.which(GIT_BINARY);
    }
    catch {
        return null;
    }
});
/**
 * Prints server debug into into the console.
 *
 * @param {{
 * driverConfig: import('./extension/driver-config').DriverConfig,
 * pluginConfig: import('./extension/plugin-config').PluginConfig,
 * appiumHome: string
 * }} info
 * @returns {Promise<void>}
 */
async function showDebugInfo({ driverConfig, pluginConfig, appiumHome }) {
    const getNpmVersion = async () => {
        const { stdout } = await support_1.npm.exec('--version', [], { cwd: process.cwd() });
        return lodash_1.default.trim(stdout);
    };
    const findNpmLocation = async () => await support_1.fs.which(support_1.system.isWindows() ? 'npm.cmd' : 'npm');
    const [npmVersion, npmLocation] = await bluebird_1.default.all([
        ...([getNpmVersion, findNpmLocation].map((f) => getSafeResult(f, 'unknown'))),
        /** @type {any} */ (updateBuildInfo()),
    ]);
    const debugInfo = {
        os: {
            platform: node_os_1.default.platform(),
            release: node_os_1.default.release(),
            arch: node_os_1.default.arch(),
            homedir: node_os_1.default.homedir(),
            username: node_os_1.default.userInfo().username,
        },
        node: {
            version: process.version,
            arch: process.arch,
            cwd: process.cwd(),
            argv: process.argv,
            env: process.env,
            npm: {
                location: npmLocation,
                version: npmVersion,
            },
        },
        appium: {
            location: exports.rootDir,
            homedir: appiumHome,
            build: getBuildInfo(),
            drivers: driverConfig.installedExtensions,
            plugins: pluginConfig.installedExtensions,
        },
    };
    console.log(JSON.stringify(debugInfo, null, 2));
}
/**
 * @param {boolean} [useGithubApiFallback]
 * @returns {Promise<string?>}
 */
async function getGitRev(useGithubApiFallback = false) {
    const fullGitPath = await getFullGitPath();
    if (fullGitPath) {
        try {
            const { stdout } = await (0, teen_process_1.exec)(fullGitPath, ['rev-parse', 'HEAD'], {
                cwd: __dirname,
            });
            return stdout.trim();
        }
        catch { }
    }
    if (!useGithubApiFallback) {
        return null;
    }
    // If the package folder is not a valid git repository
    // then fetch the corresponding tag info from GitHub
    try {
        return (await axios_1.default.get(`${GITHUB_API}/git/refs/tags/appium@${exports.APPIUM_VER}`, {
            headers: {
                'User-Agent': `Appium ${exports.APPIUM_VER}`,
            },
        })).data?.object?.sha;
    }
    catch { }
    return null;
}
/**
 * @param {string} commitSha
 * @param {boolean} [useGithubApiFallback]
 * @returns {Promise<string?>}
 */
async function getGitTimestamp(commitSha, useGithubApiFallback = false) {
    const fullGitPath = await getFullGitPath();
    if (fullGitPath) {
        try {
            const { stdout } = await (0, teen_process_1.exec)(fullGitPath, ['show', '-s', '--format=%ci', commitSha], {
                cwd: __dirname,
            });
            return stdout.trim();
        }
        catch { }
    }
    if (!useGithubApiFallback) {
        return null;
    }
    try {
        return (await axios_1.default.get(`${GITHUB_API}/git/tags/${commitSha}`, {
            headers: {
                'User-Agent': `Appium ${exports.APPIUM_VER}`,
            },
        })).data?.tagger?.date;
    }
    catch { }
    return null;
}
/**
 * Mutable object containing Appium build information. By default it
 * only contains the Appium version, but is updated with the build timestamp
 * and git commit hash asynchronously as soon as `updateBuildInfo` is called
 * and succeeds.
 * @returns {import('appium/types').BuildInfo}
 */
function getBuildInfo() {
    return BUILD_INFO;
}
/**
 * @returns {void}
 * @throws {Error} If Node version is outside of the supported range
 */
function checkNodeOk() {
    const version = getNodeVersion();
    if (!semver.satisfies(version, MIN_NODE_VERSION)) {
        throw new Error(`Node version must be at least ${MIN_NODE_VERSION}; current is ${version.version}`);
    }
}
async function showBuildInfo() {
    await updateBuildInfo(true);
    console.log(JSON.stringify(getBuildInfo()));
}
/**
 * Returns k/v pairs of server arguments which are _not_ the defaults.
 * @param {Args} parsedArgs
 * @returns {Args}
 */
function getNonDefaultServerArgs(parsedArgs) {
    /**
     * Flattens parsed args into a single level object for comparison with
     * flattened defaults across server args and extension args.
     * @param {Args} args
     * @returns {Record<string, { value: any, argSpec: ArgSpec }>}
     */
    const flatten = (args) => {
        const argSpecs = (0, schema_1.getAllArgSpecs)();
        const flattened = lodash_1.default.reduce([...argSpecs.values()], (acc, argSpec) => {
            if (lodash_1.default.has(args, argSpec.dest)) {
                acc[argSpec.dest] = { value: lodash_1.default.get(args, argSpec.dest), argSpec };
            }
            return acc;
        }, 
        /** @type {Record<string, { value: any, argSpec: ArgSpec }>} */ ({}));
        return flattened;
    };
    const args = flatten(parsedArgs);
    // hopefully these function names are descriptive enough
    const typesDiffer = /** @param {string} dest */ (dest) => typeof args[dest].value !== typeof defaultsFromSchema[dest];
    const defaultValueIsArray = /** @param {string} dest */ (dest) => lodash_1.default.isArray(defaultsFromSchema[dest]);
    const argsValueIsArray = /** @param {string} dest */ (dest) => lodash_1.default.isArray(args[dest].value);
    const arraysDiffer = /** @param {string} dest */ (dest) => lodash_1.default.gt(lodash_1.default.size(lodash_1.default.difference(args[dest].value, defaultsFromSchema[dest])), 0);
    const valuesDiffer = /** @param {string} dest */ (dest) => args[dest].value !== defaultsFromSchema[dest];
    const defaultIsDefined = /** @param {string} dest */ (dest) => !lodash_1.default.isUndefined(defaultsFromSchema[dest]);
    // note that `_.overEvery` is like an "AND", and `_.overSome` is like an "OR"
    const argValueNotArrayOrArraysDiffer = lodash_1.default.overSome([lodash_1.default.negate(argsValueIsArray), arraysDiffer]);
    const defaultValueNotArrayAndValuesDiffer = lodash_1.default.overEvery([
        lodash_1.default.negate(defaultValueIsArray),
        valuesDiffer,
    ]);
    /**
     * This used to be a hideous conditional, but it's broken up into a hideous function instead.
     * hopefully this makes things a little more understandable.
     * - checks if the default value is defined
     * - if so, and the default is not an array:
     *   - ensures the types are the same
     *   - ensures the values are equal
     * - if so, and the default is an array:
     *   - ensures the args value is an array
     *   - ensures the args values do not differ from the default values
     * @type {(dest: string) => boolean}
     */
    const isNotDefault = lodash_1.default.overEvery([
        defaultIsDefined,
        lodash_1.default.overSome([
            typesDiffer,
            lodash_1.default.overEvery([defaultValueIsArray, argValueNotArrayOrArraysDiffer]),
            defaultValueNotArrayAndValuesDiffer,
        ]),
    ]);
    const defaultsFromSchema = (0, schema_1.getDefaultsForSchema)(true);
    return lodash_1.default.reduce(lodash_1.default.pickBy(args, (__, key) => isNotDefault(key)), 
    // explodes the flattened object back into nested one
    (acc, { value, argSpec }) => lodash_1.default.set(acc, argSpec.dest, value), 
    /** @type {Args} */ ({}));
}
/**
 * Compacts an object for {@link showConfig}:
 * 1. Removes `subcommand` key/value
 * 2. Removes `undefined` values
 * 3. Removes empty objects (but not `false` values)
 * Does not operate recursively.
 */
const compactConfig = lodash_1.default.partial(lodash_1.default.omitBy, lodash_1.default, (value, key) => key === 'subcommand' || lodash_1.default.isUndefined(value) || (lodash_1.default.isObject(value) && lodash_1.default.isEmpty(value)));
/**
 * Shows a breakdown of the current config after CLI params, config file loaded & defaults applied.
 *
 * The actual shape of `preConfigParsedArgs` and `defaults` does not matter for the purposes of this function,
 * but it's intended to be called with values of type {@link ParsedArgs} and `DefaultValues<true>`, respectively.
 *
 * @param {Partial<ParsedArgs>} nonDefaultPreConfigParsedArgs - Parsed CLI args (or param to `init()`) before config & defaults applied
 * @param {import('./config-file').ReadConfigFileResult} configResult - Result of attempting to load a config file.  _Must_ be normalized
 * @param {Partial<ParsedArgs>} defaults - Configuration defaults from schemas
 * @param {ParsedArgs} parsedArgs - Entire parsed args object
 */
function showConfig(nonDefaultPreConfigParsedArgs, configResult, defaults, parsedArgs) {
    console.log('Appium Configuration\n');
    console.log('from defaults:\n');
    console.dir(compactConfig(defaults));
    if (configResult.config) {
        console.log(`\nfrom config file at ${configResult.filepath}:\n`);
        console.dir(compactConfig(configResult.config));
    }
    else {
        console.log(`\n(no configuration file loaded)`);
    }
    const compactedNonDefaultPreConfigArgs = compactConfig(nonDefaultPreConfigParsedArgs);
    if (lodash_1.default.isEmpty(compactedNonDefaultPreConfigArgs)) {
        console.log(`\n(no CLI parameters provided)`);
    }
    else {
        console.log('\nvia CLI or function call:\n');
        console.dir(compactedNonDefaultPreConfigArgs);
    }
    console.log('\nfinal configuration:\n');
    console.dir(compactConfig(parsedArgs));
}
/**
 * @param {string} root
 * @param {boolean} [requireWriteable=true]
 * @param {string} [displayName='folder path']
 * @throws {Error}
 */
async function requireDir(root, requireWriteable = true, displayName = 'folder path') {
    let stat;
    try {
        stat = await support_1.fs.stat(root);
    }
    catch (e) {
        if (e.code === 'ENOENT') {
            try {
                await support_1.fs.mkdir(root, { recursive: true });
                return;
            }
            catch { }
        }
        throw new Error(`The ${displayName} '${root}' must exist and be a valid directory`);
    }
    if (stat && !stat.isDirectory()) {
        throw new Error(`The ${displayName} '${root}' must be a valid directory`);
    }
    if (requireWriteable) {
        try {
            await support_1.fs.access(root, support_1.fs.constants.W_OK);
        }
        catch {
            throw new Error(`The ${displayName} '${root}' must be ` +
                `writeable for the current user account '${node_os_1.default.userInfo().username}'`);
        }
    }
}
/**
 * Calculates the result of the given function and return its value
 * or the default one if there was an exception.
 *
 * @template T
 * @param {() => Promise<T>} f
 * @param {T} defaultValue
 * @returns {Promise<T>}
 */
async function getSafeResult(f, defaultValue) {
    try {
        return await f();
    }
    catch {
        return defaultValue;
    }
}
/**
 * @typedef {import('appium/types').ParsedArgs} ParsedArgs
 * @typedef {import('appium/types').Args} Args
 * @typedef {import('./schema/arg-spec').ArgSpec} ArgSpec
 */
//# sourceMappingURL=config.js.map