package com.example.binettest.data.entry_list.storage.db

import androidx.room.*
import com.example.binettest.data.entry_list.storage.db.models.EntryDetailsDB

// Data Access Object
@Dao
interface RoomDao {

    @Query("SELECT * FROM view_entry WHERE id = 0")
    fun getEntry(): EntryDetailsDB

    @Delete
    fun deleteEntry(entry: EntryDetailsDB)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEntry(entry: EntryDetailsDB)
}
