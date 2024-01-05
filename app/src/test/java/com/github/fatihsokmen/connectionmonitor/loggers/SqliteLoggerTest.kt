package com.github.fatihsokmen.connectionmonitor.loggers

import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class SqliteLoggerTest {

    private val db = mockk<DatabaseFacade>(relaxed = true)
    private val subject = SqliteLogger(db)

    @Test
    fun `GIVEN dd logger WHEN url is logged THEN url is inserted in DB`() {
        subject.log("example-url")

        verify { db.insert("example-url") }
    }
}