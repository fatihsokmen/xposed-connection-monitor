package com.github.fatihsokmen.connectionmonitor.hooks

import com.github.fatihsokmen.connectionmonitor.loggers.UrlLogger
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.XC_MethodHook as MethodHook

class OkkHttpConnectionHook(private val classLoader: ClassLoader, private val logger: UrlLogger) :
    ConnectionHook {

    override fun install() {
        val exchange = XposedHelpers.findClassIfExists(
            "okhttp3.internal.connection.Exchange",
            classLoader
        )

        XposedHelpers.findAndHookMethod(
            exchange,
            "writeRequestHeaders",
            "okhttp3.Request",
            Handler(logger)
        )
    }

    class Handler(private val logger: UrlLogger) : MethodHook() {
        public override fun beforeHookedMethod(param: MethodHookParam) {
            try {
                val url = XposedHelpers.getObjectField(param.args[0], "url").toString()
                logger.log(url)
            } catch (e: Exception) {
                XposedBridge.log("Failed to get request url")
            }
        }
    }
}