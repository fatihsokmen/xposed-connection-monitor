package com.github.fatihsokmen.connectionmonitor.di

import com.github.fatihsokmen.connectionmonitor.loggers.ConsoleLogger
import com.github.fatihsokmen.connectionmonitor.loggers.SqliteLogger
import com.github.fatihsokmen.connectionmonitor.loggers.UrlLogger
import org.koin.core.module.Module
import org.koin.dsl.module

val loggers: Module = module {
    single { SqliteLogger(get()) }
    single { ConsoleLogger() }
    single { UrlLogger(get(), get()) }
}