package com.github.fatihsokmen.connectionmonitor.loggers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Strategy
 */
class UrlLogger(
    private val sqliteLogger: SqliteLogger,
    private val consoleLogger: ConsoleLogger
) {
    /**
     * Scope for io operations, not to block main thread
     */
    private val scope = CoroutineScope(Dispatchers.IO + Job())

    /**
     * Run loggers in separate coroutines, order is not important
     */
    fun log(url: String) {
        scope.launch {
            consoleLogger.log(url)
        }
        scope.launch {
            sqliteLogger.log(url)
        }
    }

}