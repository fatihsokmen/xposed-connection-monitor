package com.github.fatihsokmen.connectionmonitor

import com.github.fatihsokmen.connectionmonitor.di.crateModuleComponent
import com.github.fatihsokmen.connectionmonitor.hooks.ConnectionHook
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage
import org.koin.core.component.KoinComponent

class UrlConnectionMonitorModule : IXposedHookLoadPackage, KoinComponent {

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
        if (lpparam?.classLoader != null) {
            crateModuleComponent(lpparam.classLoader)
            installHooks()
        } else {
            XposedBridge.log("Xposed module cannot be installed as class loader is null")
        }
    }

    private fun installHooks() {
        getKoin().getAll<ConnectionHook>().forEach {
            XposedBridge.log(">>> Installing $it")
            it.install()
        }
    }
}