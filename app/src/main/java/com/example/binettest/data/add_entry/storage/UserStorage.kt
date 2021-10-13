package com.example.binettest.data.add_entry.storage

import com.example.binettest.data.add_entry.storage.models.UserSession

interface UserStorage {
    fun getSessionValue(): UserSession?
}