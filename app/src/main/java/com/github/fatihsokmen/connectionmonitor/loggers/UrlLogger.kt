package com.github.fatihsokmen.connectionmonitor.loggers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Strategy
 */
class UrlLogger(
    private val sqliteLogger: SqliteLogger,
    private val consoleLogger: ConsoleLogger,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + Job())
) {
    /**
     * Run loggers in separate coroutines, order is not important
     */
    fun log(url: String) {
        coroutineScope.launch {
            consoleLogger.log(url)
        }
        coroutineScope.launch {
            sqliteLogger.log(url)
        }
    }

}