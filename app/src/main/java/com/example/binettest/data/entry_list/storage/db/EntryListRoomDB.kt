package com.example.binettest.data.entry_list.storage.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.binettest.data.entry_list.storage.db.models.EntryDetailsDB

@Database(entities = [EntryDetailsDB::class], version = 1, exportSchema = false)
abstract class EntryListRoomDB : RoomDatabase() {

    abstract fun getRoomDao(): RoomDao

    companion object {

        @Volatile
        private var database: EntryListRoomDB? = null

        @Synchronized
        fun getInstance(context: Context): EntryListRoomDB {
            return if (database == null) {
                database = Room
                    .databaseBuilder(
                        context,
                        EntryListRoomDB::class.java,
                        "database"
                    )
                    .fallbackToDestructiveMigration().build()

                database as EntryListRoomDB
            } else database as EntryListRoomDB
        }
    }
}
