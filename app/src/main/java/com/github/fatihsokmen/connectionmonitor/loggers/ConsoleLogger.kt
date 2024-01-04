package com.github.fatihsokmen.connectionmonitor.loggers

import de.robv.android.xposed.XposedBridge

class ConsoleLogger : Logger {
    override fun log(url: String) {
        XposedBridge.log(url)
    }
}