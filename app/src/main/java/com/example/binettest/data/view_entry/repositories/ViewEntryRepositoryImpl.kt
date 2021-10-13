package com.example.binettest.data.view_entry.repositories

import com.example.binettest.data.view_entry.storage.UserStorageEntry
import com.example.binettest.data.view_entry.storage.models.WholeEntry
import com.example.binettest.domain.view_entry.models.EntryDetailsModel
import com.example.binettest.domain.view_entry.repositories.ViewEntryRepository
import io.reactivex.rxjava3.core.Single

class ViewEntryRepositoryImpl(
    private val userStorage: UserStorageEntry
) : ViewEntryRepository {

    override fun getEntryDetails(): Single<EntryDetailsModel> {
        return mapToDomain(userStorage.getWholeEntry())
    }

    override fun deleteEntry() {
        userStorage.deleteEntry()
    }

    private fun mapToDomain(entry: WholeEntry): Single<EntryDetailsModel> {
        return Single.just(
            EntryDetailsModel(
                dateAdded = entry.dateAdded,
                dateModified = entry.dateModified,
                bodyText = entry.bodyText
            )
        )
    }

    private fun mapToStorage(entry: EntryDetailsModel?): WholeEntry {
        return WholeEntry(
            dateAdded = entry?.dateAdded,
            dateModified = entry?.dateModified,
            bodyText = entry?.bodyText
        )
    }
}
