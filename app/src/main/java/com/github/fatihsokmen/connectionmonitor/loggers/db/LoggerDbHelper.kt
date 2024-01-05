package com.github.fatihsokmen.connectionmonitor.loggers.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.github.fatihsokmen.connectionmonitor.loggers.db.DbLoggerContract.TABLE_NAME

/**
 * I will skip slqdelight and room as module has a simple logging structure
 * and I will use standard android sqlite db approach to avoid additional compile time dependencies
 */
object DbLoggerContract {
    const val TABLE_NAME = "url_logs"

    object Entry {
        const val TIME = "time"
        const val URL = "url"
    }
}

private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE $TABLE_NAME (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "${DbLoggerContract.Entry.TIME} TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "${DbLoggerContract.Entry.URL} TEXT)"

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"

class LoggerDbHelper(context: Context) :
    SQLiteOpenHelper(context, "UrlLogger.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    /**
     * We delete all rows on upgrade
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}