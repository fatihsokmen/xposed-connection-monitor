package com.github.fatihsokmen.connectionmonitor.hooks

import android.app.AndroidAppHelper
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers

class OkkHttpConnectionHook(private val classLoader: ClassLoader) : ConnectionHook {

    override fun install() {
        XposedBridge.log(">>> Running: OkkHttpConnectionHook.install")
        val exchange = XposedHelpers.findClassIfExists(
            "okhttp3.internal.connection.Exchange",
            classLoader
        )

        XposedHelpers.findAndHookMethod(
            exchange,
            "writeRequestHeaders",
            "okhttp3.Request",
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam?) {
                    try {
                        // Get the okhttp3.HttpUrl object from the Request
                        val url = XposedHelpers.getObjectField(param!!.args[0], "url").toString()
                        XposedBridge.log("URL: $url")
                        XposedBridge.log(AndroidAppHelper.currentApplication().packageName)
                    } catch (e: Exception) {
                        XposedBridge.log("failed to get request url")
                    }
                }
            }
        )
    }
}