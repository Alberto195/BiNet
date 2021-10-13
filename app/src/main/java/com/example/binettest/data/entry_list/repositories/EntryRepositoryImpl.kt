package com.example.binettest.data.entry_list.repositories

import com.example.binettest.domain.entry_list.repositories.EntryRepository
import com.example.binettest.data.entry_list.api_service.EntryApi
import com.example.binettest.data.entry_list.mappers.EntryListMapper
import com.example.binettest.data.entry_list.storage.UserStorageDB
import com.example.binettest.domain.entry_list.models.EntryListModel
import com.example.binettest.data.entry_list.storage.UserStorageSharedPrefs
import com.example.binettest.data.entry_list.storage.db.models.EntryDetailsDB
import io.reactivex.rxjava3.core.Single

class EntryRepositoryImpl (
    private val api: EntryApi,
    private val userStorageSharedPrefs: UserStorageSharedPrefs,
    private val userStorageDB: UserStorageDB,
    private val entryListMapper: EntryListMapper,
) : EntryRepository {

    override fun getEntry(): Single<List<EntryListModel>?> {
        val call = api.getEntries("get_entries", userStorageSharedPrefs.getSessionValue()?.sessionId)

        return entryListMapper.toEntryListModel(call.execute().body())
    }

    override fun setEntry(entry: EntryListModel?) {
        userStorageDB.setEntry(mapToStorageDB(entry))
    }

    private fun mapToStorageDB(entry: EntryListModel?): EntryDetailsDB {
        return EntryDetailsDB(
            id = 0,
            dateAdded = entry?.dateAdded,
            dateModified = entry?.dateModified,
            bodyText = entry?.entryText
        )
    }
}
