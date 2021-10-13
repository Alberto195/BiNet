package com.example.binettest.domain.add_entry.usecases

import com.example.binettest.domain.add_entry.models.AddEntryModel
import com.example.binettest.domain.add_entry.repositories.AddEntryRepository

class AddEntryUseCase(
        private val repository: AddEntryRepository
) {

    fun execute(addEntryModel: AddEntryModel) {
        return repository.addEntry(addEntryModel)
    }
}