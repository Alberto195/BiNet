package com.example.binettest.data.view_entry.storage.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.binettest.data.view_entry.storage.db.models.EntryDetailsDB

@Database(entities = [EntryDetailsDB::class], version = 1)
abstract class ViewEntryRoomDB : RoomDatabase() {

    abstract fun getRoomDao(): RoomDao

    companion object {

        @Volatile
        private var database: ViewEntryRoomDB? = null

        @Synchronized
        fun getInstance(context: Context): ViewEntryRoomDB {
            return if (database == null) {
                database = Room
                    .databaseBuilder(
                        context,
                        ViewEntryRoomDB::class.java,
                        "database"
                    )
                    .fallbackToDestructiveMigration().build()

                database as ViewEntryRoomDB
            } else database as ViewEntryRoomDB
        }
    }
}
