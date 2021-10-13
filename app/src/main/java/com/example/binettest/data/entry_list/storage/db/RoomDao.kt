package com.example.binettest.data.entry_list.storage.db

import androidx.room.*
import com.example.binettest.data.entry_list.storage.db.models.EntryDetailsDB

// Data Access Object
@Dao
interface RoomDao {

    @Query("SELECT * FROM view_entry;")
    fun getEntry(): EntryDetailsDB

    @Query("DELETE FROM view_entry;")
    fun deleteEntry()


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEntry(entry: EntryDetailsDB)
}
