package com.github.fatihsokmen.connectionmonitor

import com.github.fatihsokmen.connectionmonitor.di.crateModuleComponent
import com.github.fatihsokmen.connectionmonitor.hooks.ConnectionHook
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage
import org.koin.core.component.KoinComponent

/**
 * This class is entry-point of module to monitor urls called.
 * Here are steps to init module code
 *  1 - A check is being done to see if we are monitoring  com.datatheorem.xposedtest package
 *  2 - Dependency injection graph is been created to separate concerns and make them as testable as we can
 *  3 - Install hooks to monitor urls called
 */
class UrlConnectionMonitorModule : IXposedHookLoadPackage, KoinComponent {

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
        if (!lpparam?.packageName.equals("com.datatheorem.xposedtest"))
            return

        if (lpparam?.classLoader != null) {
            crateModuleComponent(lpparam.classLoader)
            installHooks()
        } else {
            XposedBridge.log("Xposed module cannot be installed as class loader is null")
            return
        }
    }

    private fun installHooks() {
        getKoin().getAll<ConnectionHook>()
            .forEach {
                XposedBridge.log(">>> Installing hook: $it")
                it.install()
            }
    }
}