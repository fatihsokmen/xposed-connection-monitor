package com.github.fatihsokmen.connectionmonitor.hooks

import org.koin.core.component.KoinComponent

interface ConnectionHook : KoinComponent {

    fun install()
}