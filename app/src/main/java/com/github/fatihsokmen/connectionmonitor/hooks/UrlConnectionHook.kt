package com.github.fatihsokmen.connectionmonitor.hooks

import com.github.fatihsokmen.connectionmonitor.loggers.UrlLogger
import de.robv.android.xposed.XC_MethodHook as MethodHook

class UrlConnectionHook(
    private val xposedFacade: XposedHelpersFacade,
    private val handler: UrlConnectionHook.Handler,
    private val classLoader: ClassLoader
) :
    ConnectionHook {

    override fun install() {
        xposedFacade.findAndHookConstructor(
            "java.net.URL",
            classLoader,
            String::class.java,
            handler
        )
    }

    class Handler(private val logger: UrlLogger) : MethodHook() {
        public override fun beforeHookedMethod(param: MethodHookParam) {
            logger.log(param.args[0].toString())
        }
    }
}