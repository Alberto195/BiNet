package com.example.binettest.data.view_entry.storage

import com.example.binettest.data.view_entry.storage.models.WholeEntry

interface UserStorageEntry {

    fun getWholeEntry(): WholeEntry

    fun deleteEntry()
}
