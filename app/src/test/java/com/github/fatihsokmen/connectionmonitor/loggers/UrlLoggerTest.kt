package com.github.fatihsokmen.connectionmonitor.loggers

import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UrlLoggerTest {

    private val sqliteLogger = mockk<SqliteLogger>(relaxed = true)
    private val consoleLogger = mockk<ConsoleLogger>(relaxed = true)
    private val coroutineScope = TestScope()

    @Test
    fun `GIVEN UrlLogger WHEN ur is logged THEN url is logged in db and console`() =
        coroutineScope.runTest {
            UrlLogger(sqliteLogger, consoleLogger, coroutineScope)
                .log("example-url")

            advanceUntilIdle()

            verify { sqliteLogger.log("example-url") }
            verify { consoleLogger.log("example-url") }
        }

}