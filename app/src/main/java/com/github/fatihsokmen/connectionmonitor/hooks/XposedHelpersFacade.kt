package com.github.fatihsokmen.connectionmonitor.hooks

import de.robv.android.xposed.XposedHelpers

/**
 * This is for to be able to write unit tests.
 * I cant mockStatic Xposed methods due to Android framework dependencies and external class dependencies
 */
open class XposedHelpersFacade {

    fun findAndHookConstructor(
        className: String,
        classLoader: ClassLoader,
        vararg argTypesAndHandler: Any
    ) {
        XposedHelpers.findAndHookConstructor(
            className,
            classLoader,
            *argTypesAndHandler
        )
    }

    fun findAndHookMethod(
        cls: Class<*>,
        methodsName: String,
        vararg argTypesAndHandler: Any
    ) {
        XposedHelpers.findAndHookMethod(
            cls,
            methodsName,
            *argTypesAndHandler
        )
    }

    fun findClassIfExists(className: String, classLoader: ClassLoader): Class<*> =
        XposedHelpers.findClassIfExists(className, classLoader)

    fun getObjectField(obj: Any, fieldName: String) =
        XposedHelpers.getObjectField(obj, fieldName)

}