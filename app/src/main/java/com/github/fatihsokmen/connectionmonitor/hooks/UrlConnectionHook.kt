package com.github.fatihsokmen.connectionmonitor.hooks

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers

class UrlConnectionHook(private val classLoader: ClassLoader) : ConnectionHook {

    override fun install() {
        XposedBridge.log(">>> Running: UrlConnectionHook.install")
        XposedHelpers.findAndHookConstructor(
            "java.net.URL",
            classLoader,
            String::class.java,
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    XposedBridge.log("URL: ${param.args[0]}")
                }
            }
        )
    }
}