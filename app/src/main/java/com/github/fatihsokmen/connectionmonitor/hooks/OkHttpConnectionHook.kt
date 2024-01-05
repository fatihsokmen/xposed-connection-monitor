package com.github.fatihsokmen.connectionmonitor.hooks

import com.github.fatihsokmen.connectionmonitor.loggers.UrlLogger
import de.robv.android.xposed.XC_MethodHook as MethodHook
import de.robv.android.xposed.XposedBridge

class OkHttpConnectionHook(
    private val xposedFacade: XposedHelpersFacade,
    private val handler: OkHttpConnectionHook.Handler,
    private val classLoader: ClassLoader,
) : ConnectionHook {

    override fun install() {
        val exchange = xposedFacade.findClassIfExists(
            "okhttp3.internal.connection.Exchange",
            classLoader
        )

        xposedFacade.findAndHookMethod(
            exchange,
            "writeRequestHeaders",
            "okhttp3.Request",
            handler
        )
    }

    class Handler(private val xposedFacade: XposedHelpersFacade, private val logger: UrlLogger) :
        MethodHook() {
        public override fun beforeHookedMethod(param: MethodHookParam) {
            try {
                val url = xposedFacade.getObjectField(param.args[0], "url").toString()
                logger.log(url)
            } catch (e: Exception) {
                XposedBridge.log("Failed to get request url")
            }
        }
    }
}