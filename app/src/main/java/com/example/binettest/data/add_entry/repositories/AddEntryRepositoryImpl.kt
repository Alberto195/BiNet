package com.example.binettest.data.add_entry.repositories

import com.example.binettest.data.add_entry.api_service.AddApi
import com.example.binettest.data.add_entry.storage.UserStorage
import com.example.binettest.domain.add_entry.models.AddEntryModel
import com.example.binettest.domain.add_entry.repositories.AddEntryRepository

class AddEntryRepositoryImpl(
        private val api: AddApi,
        private val userStorage: UserStorage
) : AddEntryRepository {

    override fun addEntry(addEntry: AddEntryModel) {
        val call = api.addEntry(
                a = "add_entry",
                session = userStorage.getSessionValue()?.sessionId,
                body = addEntry.bodyText
        )
        call.execute()
    }
}