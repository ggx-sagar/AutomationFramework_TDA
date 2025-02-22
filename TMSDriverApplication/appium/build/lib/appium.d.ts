/**
 * Thrown when Appium tried to proxy a command using a driver's `proxyCommand` method but the
 * method did not exist
 */
export class NoDriverProxyCommandError extends Error {
    constructor();
    /**
     * @type {Readonly<string>}
     */
    code: Readonly<string>;
}
export type DriverData = import("@appium/types").DriverData;
export type DriverOpts = import("@appium/types").ServerArgs;
export type Constraints = import("@appium/types").Constraints;
export type AppiumServer = import("@appium/types").AppiumServer;
export type ExtensionType = import("@appium/types").ExtensionType;
export type DriverConfig = import("./extension/driver-config").DriverConfig;
export type PluginType = import("@appium/types").PluginType;
export type DriverType = import("@appium/types").DriverType;
export type StringRecord = import("@appium/types").StringRecord;
export type ExternalDriver = import("@appium/types").ExternalDriver;
export type PluginClass = import("@appium/types").PluginClass;
export type Plugin = import("@appium/types").Plugin;
export type ExtensionCore = import("@appium/base-driver").ExtensionCore;
export type DriverClass = import("@appium/types").DriverClass<import("@appium/types").Driver>;
export type AppiumSessionHandler = import("@appium/types").ISessionHandler<AppiumDriverConstraints, SessionHandlerCreateResult, SessionHandlerDeleteResult>;
export type SessionHandlerCreateResult = SessionHandlerResult<[innerSessionId: string, caps: import("@appium/types").DriverCaps<Constraints>, protocol: string | undefined]>;
export type Core<C extends Constraints> = import("@appium/types").Core<C>;
export type SessionHandlerDeleteResult = SessionHandlerResult<void>;
/**
 * Used by {@linkcode AppiumDriver.createSession} and {@linkcode AppiumDriver.deleteSession} to describe
 * result.
 */
export type SessionHandlerResult<V> = {
    value?: V | undefined;
    error?: Error | undefined;
    protocol?: string | undefined;
};
export type AppiumDriverConstraints = typeof desiredCapabilityConstraints;
export type W3CAppiumDriverCaps = import("@appium/types").W3CDriverCaps<AppiumDriverConstraints>;
/**
 * @extends {DriverCore<AppiumDriverConstraints>}
 */
export class AppiumDriver extends DriverCore<{
    readonly automationName: {
        readonly presence: true;
        readonly isString: true;
    };
    readonly platformName: {
        readonly presence: true;
        readonly isString: true;
    };
}, import("@appium/types").StringRecord> {
    /**
     * @param {import('@appium/types').DriverOpts<AppiumDriverConstraints>} opts
     */
    constructor(opts: import("@appium/types").DriverOpts<AppiumDriverConstraints>);
    /**
     * Access to sessions list must be guarded with a Semaphore, because
     * it might be changed by other async calls at any time
     * It is not recommended to access this property directly from the outside
     * @type {Record<string,ExternalDriver>}
     */
    sessions: Record<string, ExternalDriver>;
    /**
     * Access to pending drivers list must be guarded with a Semaphore, because
     * it might be changed by other async calls at any time
     * It is not recommended to access this property directly from the outside
     * @type {Record<string,ExternalDriver[]>}
     */
    pendingDrivers: Record<string, ExternalDriver[]>;
    /**
     * List of active plugins
     * @type {Map<PluginClass,string>}
     */
    pluginClasses: Map<PluginClass, string>;
    /**
     * map of sessions to actual plugin instances per session
     * @type {Record<string,InstanceType<PluginClass>[]>}
     */
    sessionPlugins: Record<string, InstanceType<PluginClass>[]>;
    /**
     * some commands are sessionless, so we need a set of plugins for them
     * @type {InstanceType<PluginClass>[]}
     */
    sessionlessPlugins: InstanceType<PluginClass>[];
    /** @type {DriverConfig} */
    driverConfig: DriverConfig;
    /** @type {AppiumServer} */
    server: AppiumServer;
    /** @type {Record<string, import('ws').WebSocket[]>} */
    bidiSockets: Record<string, import("ws").WebSocket[]>;
    /** @type {Record<string, import('ws').WebSocket>} */
    bidiProxyClients: Record<string, import("ws").WebSocket>;
    /**
     * @type {AppiumDriverConstraints}
     * @readonly
     */
    readonly desiredCapConstraints: AppiumDriverConstraints;
    /** @type {import('@appium/types').DriverOpts<AppiumDriverConstraints>} */
    args: import("@appium/types").DriverOpts<AppiumDriverConstraints>;
    _isShuttingDown: boolean;
    sessionExists(sessionId: any): boolean;
    driverForSession(sessionId: any): import("@appium/types").ExternalDriver<import("@appium/types").Constraints, string, import("@appium/types").StringRecord, import("@appium/types").StringRecord, import("@appium/types").DefaultCreateSessionResult<import("@appium/types").Constraints>, void, import("@appium/types").StringRecord>;
    getStatus(): Promise<{
        build: import("../types").BuildInfo;
        ready: boolean;
        message: string;
    }>;
    /**
     * @param {string|null} reason An optional shutdown reason
     */
    shutdown(reason?: string | null): Promise<void>;
    getSessions(): Promise<{
        id: string;
        capabilities: import("@appium/types").DriverCaps<any>;
    }[]>;
    printNewSessionAnnouncement(driverName: any, driverVersion: any, driverBaseVersion: any): void;
    /**
     * Retrieves all CLI arguments for a specific plugin.
     * @param {string} extName - Plugin name
     * @returns {StringRecord} Arguments object. If none, an empty object.
     */
    getCliArgsForPlugin(extName: string): StringRecord;
    /**
     * Retrieves CLI args for a specific driver.
     *
     * _Any arg which is equal to its default value will not be present in the returned object._
     *
     * _Note that this behavior currently (May 18 2022) differs from how plugins are handled_ (see {@linkcode AppiumDriver.getCliArgsForPlugin}).
     * @param {string} extName - Driver name
     * @returns {StringRecord|undefined} Arguments object. If none, `undefined`
     */
    getCliArgsForDriver(extName: string): StringRecord | undefined;
    /**
     * Create a new session
     * @param {W3CAppiumDriverCaps} jsonwpCaps JSONWP formatted desired capabilities
     * @param {W3CAppiumDriverCaps} [reqCaps] Required capabilities (JSONWP standard)
     * @param {W3CAppiumDriverCaps} [w3cCapabilities] W3C capabilities
     * @returns {Promise<SessionHandlerCreateResult>}
     */
    createSession(jsonwpCaps: W3CAppiumDriverCaps, reqCaps?: W3CAppiumDriverCaps, w3cCapabilities?: W3CAppiumDriverCaps): Promise<SessionHandlerCreateResult>;
    /**
     *
     * @param {ExternalDriver} driver
     * @param {string} innerSessionId
     */
    attachUnexpectedShutdownHandler(driver: ExternalDriver, innerSessionId: string): void;
    /**
     *
     * @param {((...args: any[]) => any)|(new(...args: any[]) => any)} InnerDriver
     * @returns {Promise<DriverData[]>}}
     * @privateRemarks The _intent_ is that `InnerDriver` is the class of a driver, but it only really
     * needs to be a function or constructor.
     */
    curSessionDataForDriver(InnerDriver: ((...args: any[]) => any) | (new (...args: any[]) => any)): Promise<DriverData[]>;
    /**
     * @param {string} sessionId
     */
    deleteSession(sessionId: string): Promise<{
        protocol: undefined;
        value: void;
        error?: undefined;
    } | {
        protocol: undefined;
        error: any;
        value?: undefined;
    }>;
    /**
     * @param {string} sessionId
     */
    cleanupBidiSockets(sessionId: string): void;
    deleteAllSessions(opts?: {}): Promise<void>;
    /**
     * Get the appropriate plugins for a session (or sessionless plugins)
     *
     * @param {?string} sessionId - the sessionId (or null) to use to find plugins
     * @returns {Array<import('@appium/types').Plugin>} - array of plugin instances
     */
    pluginsForSession(sessionId?: string | null): Array<import("@appium/types").Plugin>;
    /**
     * To get plugins for a command, we either get the plugin instances associated with the
     * particular command's session, or in the case of sessionless plugins, pull from the set of
     * plugin instances reserved for sessionless commands (and we lazily create plugin instances on
     * first use)
     *
     * @param {string} cmd - the name of the command to find a plugin to handle
     * @param {?string} sessionId - the particular session for which to find a plugin, or null if
     * sessionless
     */
    pluginsToHandleCmd(cmd: string, sessionId?: string | null): import("@appium/types").Plugin[];
    /**
     * Creates instances of all of the enabled Plugin classes
     * @param {string|null} driverId - ID to use for linking a driver to a plugin in logs
     * @returns {Plugin[]}
     */
    createPluginInstances(driverId?: string | null): Plugin[];
    /**
     *
     * @param {string} cmd
     * @param  {...any} args
     * @returns {Promise<{value: any, error?: Error, protocol: string} | import('type-fest').AsyncReturnType<ExternalDriver['executeCommand']>>}
     */
    executeCommand(cmd: string, ...args: any[]): Promise<{
        value: any;
        error?: Error;
        protocol: string;
    } | import("type-fest").AsyncReturnType<ExternalDriver["executeCommand"]>>;
    wrapCommandWithPlugins({ driver, cmd, args, next, cmdHandledBy, plugins }: {
        driver: any;
        cmd: any;
        args: any;
        next: any;
        cmdHandledBy: any;
        plugins: any;
    }): any;
    logPluginHandlerReport(plugins: any, { cmd, cmdHandledBy }: {
        cmd: any;
        cmdHandledBy: any;
    }): void;
    executeWrappedCommand({ wrappedCmd, protocol }: {
        wrappedCmd: any;
        protocol: any;
    }): Promise<{
        value: any;
        error: any;
        protocol: any;
    }>;
    proxyActive(sessionId: any): boolean;
    canProxy(sessionId: any): boolean;
    onBidiConnection: typeof bidiHelpers.onBidiConnection;
    onBidiMessage: typeof bidiHelpers.onBidiMessage;
    onBidiServerError: typeof bidiHelpers.onBidiServerError;
}
declare namespace desiredCapabilityConstraints {
    namespace automationName {
        let presence: true;
        let isString: true;
    }
    namespace platformName {
        let presence_1: true;
        export { presence_1 as presence };
        let isString_1: true;
        export { isString_1 as isString };
    }
}
import { DriverCore } from '@appium/base-driver';
import * as bidiHelpers from './bidi';
export {};
//# sourceMappingURL=appium.d.ts.map