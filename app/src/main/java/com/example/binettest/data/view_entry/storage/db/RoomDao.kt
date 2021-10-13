package com.example.binettest.data.view_entry.storage.db

import androidx.room.*
import com.example.binettest.data.view_entry.storage.db.models.EntryDetailsDB
import io.reactivex.rxjava3.core.Single

@Dao
interface RoomDao {
    @Query("SELECT * FROM view_entry")
    fun getEntry(): EntryDetailsDB

    @Query("DELETE FROM view_entry;")
    fun deleteEntry()
}
