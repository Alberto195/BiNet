package com.example.binettest.data.entry_list.storage

import com.example.binettest.data.entry_list.storage.models.UserSession

interface UserStorageSharedPrefs {
    fun getSessionValue(): UserSession?
}
