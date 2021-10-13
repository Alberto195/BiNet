package com.example.binettest.data.view_entry.storage.db

import com.example.binettest.data.view_entry.storage.UserStorageEntry
import com.example.binettest.data.view_entry.storage.db.models.EntryDetailsDB
import com.example.binettest.data.view_entry.storage.models.WholeEntry
import io.reactivex.rxjava3.core.Single

class DatabaseEntry(
    private val roomDao: RoomDao
) : UserStorageEntry {

    override fun getWholeEntry(): WholeEntry {
        return mapToRepository(roomDao.getEntry())
    }

    override fun deleteEntry() {
        roomDao.deleteEntry()
    }

    private fun mapToRepository(entry: EntryDetailsDB?): WholeEntry {
        return WholeEntry(
            dateAdded = entry?.dateAdded,
            dateModified = entry?.dateModified,
            bodyText = entry?.bodyText
        )
    }

    private fun mapToDatabase(entry: WholeEntry): EntryDetailsDB {
        return EntryDetailsDB(
            id = 1,
            dateAdded = entry.dateAdded,
            dateModified = entry.dateModified,
            bodyText = entry.bodyText
        )
    }
}