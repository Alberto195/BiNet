package com.example.binettest.domain.entry_list.repositories

import com.example.binettest.domain.entry_list.models.EntryListModel
import com.example.binettest.domain.core.models.UserSessionId
import io.reactivex.rxjava3.core.Single

interface EntryRepository {
    fun getEntry(): Single<List<EntryListModel>?>

    fun setEntry(entry: EntryListModel?)
}
