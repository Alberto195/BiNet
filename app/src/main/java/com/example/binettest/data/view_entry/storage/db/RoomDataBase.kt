package com.example.binettest.data.view_entry.storage.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.binettest.data.view_entry.storage.db.models.EntryDetailsDB

@Database(entities = [EntryDetailsDB::class], version = 1)
abstract class RoomDataBase : RoomDatabase() {

    abstract fun getRoomDao(): RoomDao

    companion object {

        @Volatile
        private var database: RoomDataBase? = null

        @Synchronized
        fun getInstance(context: Context): RoomDataBase {
            return if (database == null) {
                database = Room
                    .databaseBuilder(
                        context,
                        RoomDataBase::class.java,
                        "database"
                    )
                    .fallbackToDestructiveMigration().build()

                database as RoomDataBase
            } else database as RoomDataBase
        }
    }
}
