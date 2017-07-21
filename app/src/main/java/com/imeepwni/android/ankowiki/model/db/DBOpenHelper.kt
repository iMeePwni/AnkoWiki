package com.imeepwni.android.ankowiki.model.db

import android.database.sqlite.*
import com.imeepwni.android.ankowiki.app.*
import com.imeepwni.android.ankowiki.model.data.*
import org.jetbrains.anko.db.*

/**
 * Create by guofeng on 2017/7/21.
 */
class DBOpenHelper : ManagedSQLiteOpenHelper(App.getInstance(), DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(User.TABLE_NAME, true,
                User.ID to INTEGER + PRIMARY_KEY,
                User.NAME to TEXT,
                User.AGE to INTEGER,
                User.SEX to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(User.TABLE_NAME, true)
        onCreate(db)
    }

    companion object {
        val DB_NAME = "DB"
        val DB_VERSION = 1

        val instance by lazy {
            DBOpenHelper()
        }
    }

//    // Access property for Context
//    val Context.db: DBOpenHelper
//        get() = getInstance()
//    // Access property for Fragment
//    val Fragment.db: DBOpenHelper
//        get() = getInstance()
}