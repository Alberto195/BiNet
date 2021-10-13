package com.example.binettest.domain.entry_list.usecases

import com.example.binettest.domain.entry_list.models.EntryListModel
import com.example.binettest.domain.entry_list.repositories.EntryRepository
import io.reactivex.rxjava3.core.Single

class GetEntryListUseCase(
        private val repository: EntryRepository
) {

    fun execute(): Single<List<EntryListModel>?> {
        return repository.getEntry()
    }

}