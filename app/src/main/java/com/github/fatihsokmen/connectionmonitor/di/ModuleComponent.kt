package com.github.fatihsokmen.connectionmonitor.di

import com.github.fatihsokmen.connectionmonitor.UrlConnectionMonitorModule
import org.koin.core.context.startKoin

fun UrlConnectionMonitorModule.crateModuleComponent(classLoader: ClassLoader) = startKoin {
    modules(
        hooks(classLoader),
        persistence,
        loggers
    )
}