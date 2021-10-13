package com.example.binettest.domain.entry_list.usecases

import com.example.binettest.domain.entry_list.models.EntryListModel
import com.example.binettest.domain.entry_list.repositories.EntryRepository

class SetViewEntryUseCase(
    private val repository: EntryRepository
) {

    fun execute(entry: EntryListModel?) {
        return repository.setEntry(entry)
    }
}