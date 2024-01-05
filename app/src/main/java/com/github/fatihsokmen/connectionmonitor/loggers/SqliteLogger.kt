package com.github.fatihsokmen.connectionmonitor.loggers

import android.app.AndroidAppHelper
import android.content.ContentValues
import com.github.fatihsokmen.connectionmonitor.loggers.db.DbLoggerContract
import com.github.fatihsokmen.connectionmonitor.loggers.db.LoggerDbHelper
import org.koin.core.component.KoinComponent

class SqliteLogger(private val db: DatabaseFacade) : Logger, KoinComponent {
    override fun log(url: String) {
        db.insert(url)
    }
}

/**
 * We need this because [android.content.Context] is not immediately available when
 * dependency graph is been created.
 */
class DatabaseFacade {

    // Open for testing
    val db by lazy {
        LoggerDbHelper(AndroidAppHelper.currentApplication()).writableDatabase
    }

    fun insert(url: String) {
        val row = ContentValues().apply {
            put(DbLoggerContract.Entry.URL, url)
        }
        db.insert(DbLoggerContract.TABLE_NAME, null, row)
    }
}