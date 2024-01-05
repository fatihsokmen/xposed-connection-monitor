package com.github.fatihsokmen.connectionmonitor.di

import org.koin.core.context.startKoin

fun crateModuleComponent(classLoader: ClassLoader) = startKoin {
    modules(
        hooks(classLoader),
        persistence,
        loggers
    )
}