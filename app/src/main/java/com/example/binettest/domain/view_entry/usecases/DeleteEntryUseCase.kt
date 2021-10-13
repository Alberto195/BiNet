package com.example.binettest.domain.view_entry.usecases

import com.example.binettest.domain.view_entry.repositories.ViewEntryRepository

class DeleteEntryUseCase(
    private val repository: ViewEntryRepository
) {

    fun execute() {
        repository.deleteEntry()
    }
}