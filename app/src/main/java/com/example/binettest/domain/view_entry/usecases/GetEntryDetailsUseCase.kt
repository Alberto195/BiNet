package com.example.binettest.domain.view_entry.usecases

import com.example.binettest.domain.view_entry.models.EntryDetailsModel
import com.example.binettest.domain.view_entry.repositories.ViewEntryRepository
import io.reactivex.rxjava3.core.Single

class GetEntryDetailsUseCase(
        private val repository: ViewEntryRepository
) {

    fun execute(): Single<EntryDetailsModel> {
        return repository.getEntryDetails()
    }
}