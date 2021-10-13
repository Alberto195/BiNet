package com.example.binettest.data.entry_list.storage.db

import com.example.binettest.data.entry_list.storage.UserStorageDB
import com.example.binettest.data.entry_list.storage.db.models.EntryDetailsDB

class DatabaseRepositoryImpl(
    private val roomDao: RoomDao
) : UserStorageDB {

    override fun setEntry(entry: EntryDetailsDB) {
        roomDao.insertEntry(entry = entry)
    }
}
