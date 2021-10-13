package com.example.binettest.data.entry_list.mappers

import com.example.binettest.data.entry_list.models.EntryDataListModel
import com.example.binettest.domain.entry_list.models.EntryListModel
import io.reactivex.rxjava3.core.Single

class EntryListMapper() {

    fun toEntryListModel(dataList: EntryDataListModel?): Single<List<EntryListModel>?> {
        val data = dataList?.data?.get(0)?.map {
            EntryListModel(
                dateAdded = it.da,
                dateModified = it.dm,
                entryText = it.body
            )
        }

        return Single.just(data)
    }
}