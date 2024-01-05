package com.github.fatihsokmen.connectionmonitor.di

import com.github.fatihsokmen.connectionmonitor.hooks.ConnectionHook
import com.github.fatihsokmen.connectionmonitor.hooks.OkkHttpConnectionHook
import com.github.fatihsokmen.connectionmonitor.hooks.UrlConnectionHook
import com.github.fatihsokmen.connectionmonitor.hooks.XposedHelpersFacade
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

fun hooks(classLoader: ClassLoader): Module = module {

    single {
        UrlConnectionHook(
            XposedHelpersFacade(),
            UrlConnectionHook.Handler(get()),
            classLoader
        )
    } bind ConnectionHook::class

    single {
        OkkHttpConnectionHook(classLoader, get())
    } bind ConnectionHook::class
}