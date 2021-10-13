package com.example.binettest.data.entry_list.storage.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "view_entry", indices = [Index("id")])
data class EntryDetailsDB(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo
    val dateAdded: String? = "",
    @ColumnInfo
    val dateModified: String? = "",
    @ColumnInfo
    val bodyText: String? = ""
) : Serializable