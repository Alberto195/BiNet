package com.example.binettest.domain.view_entry.repositories

import com.example.binettest.domain.view_entry.models.EntryDetailsModel
import io.reactivex.rxjava3.core.Single

interface ViewEntryRepository {

    fun getEntryDetails(): Single<EntryDetailsModel>

    fun deleteEntry()
}