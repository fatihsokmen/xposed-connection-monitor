package com.github.fatihsokmen.connectionmonitor.hooks

import de.robv.android.xposed.XposedHelpers

/**
 * This is for to write unit tests.
 * For some reasons, I cant mockStatic Xposed. There are lot of runtime classes are missing
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
            argTypesAndHandler
        )
    }

}