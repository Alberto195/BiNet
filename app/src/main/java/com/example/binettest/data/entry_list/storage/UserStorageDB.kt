package com.example.binettest.data.entry_list.storage

import com.example.binettest.data.entry_list.storage.db.models.EntryDetailsDB

interface UserStorageDB {
    fun setEntry(entry: EntryDetailsDB)
}