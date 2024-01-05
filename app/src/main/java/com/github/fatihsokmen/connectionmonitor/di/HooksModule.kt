package com.github.fatihsokmen.connectionmonitor.di

import com.github.fatihsokmen.connectionmonitor.hooks.ConnectionHook
import com.github.fatihsokmen.connectionmonitor.hooks.OkHttpConnectionHook
import com.github.fatihsokmen.connectionmonitor.hooks.UrlConnectionHook
import com.github.fatihsokmen.connectionmonitor.hooks.XposedHelpersFacade
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

fun hooks(classLoader: ClassLoader): Module = module {
    single { XposedHelpersFacade() }
    single {
        UrlConnectionHook(
            xposedFacade = get<XposedHelpersFacade>(),
            handler = UrlConnectionHook.Handler(get()),
            classLoader = classLoader
        )
    } bind ConnectionHook::class

    single {
        OkHttpConnectionHook(
            xposedFacade = get<XposedHelpersFacade>(),
            handler = OkHttpConnectionHook.Handler(get<XposedHelpersFacade>(), get()),
            classLoader = classLoader
        )
    } bind ConnectionHook::class
}