package com.github.fatihsokmen.connectionmonitor.di

import com.github.fatihsokmen.connectionmonitor.loggers.DatabaseFacade
import org.koin.core.module.Module
import org.koin.dsl.module

val persistence: Module = module {
    single { DatabaseFacade() }
}