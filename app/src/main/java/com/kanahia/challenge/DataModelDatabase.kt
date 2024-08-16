package com.kanahia.challenge

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.kanahia.challenge.models.DataModel

@Database(entities = [DataModel::class], version = 1)
abstract class DataModelDatabase : RoomDatabase() {

    abstract fun getDao(): DataModelDAO

    companion object {

        @Volatile
        private var INSTANCE: DataModelDatabase? = null

        fun getDatabase(context: Context): DataModelDatabase {
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE =
                        Room.databaseBuilder(context, DataModelDatabase::class.java, "DataModelDB")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }

}