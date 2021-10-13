package com.example.binettest.domain.add_entry.repositories

import com.example.binettest.domain.add_entry.models.AddEntryModel

interface AddEntryRepository {
    fun addEntry(addEntry: AddEntryModel)
}