package com.poc.androidassignment.dbutils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.poc.androidassignment.model.Row

@Database(entities = [(Row::class)], version = 1)
abstract class AppDB : RoomDatabase() {

    abstract fun userDao(): UserDAO

    companion object {
        const val DB_VERSION = 1
        private var INSTANCE: AppDB? = null
        private const val DB_NAME = "androidassaignment.db"
        fun getAppDatabase(context: Context): AppDB? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<AppDB>(
                    context.applicationContext, AppDB::class.java,
                    DB_NAME
                ).allowMainThreadQueries().build()
            }
            return INSTANCE
        }
    }
}