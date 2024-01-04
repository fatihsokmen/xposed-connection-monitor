package com.github.fatihsokmen.connectionmonitor.hooks

import com.github.fatihsokmen.connectionmonitor.loggers.UrlLogger
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.XC_MethodHook as MethodHook

class UrlConnectionHook(private val classLoader: ClassLoader, private val logger: UrlLogger) :
    ConnectionHook {

    override fun install() {
        XposedHelpers.findAndHookConstructor(
            "java.net.URL",
            classLoader,
            String::class.java,
            object : MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    logger.log(param.args[0].toString())
                }
            }
        )
    }
}