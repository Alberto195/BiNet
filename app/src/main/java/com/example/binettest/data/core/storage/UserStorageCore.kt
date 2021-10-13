package com.example.binettest.data.core.storage

import com.example.binettest.data.core.storage.models.UserSessionCore

interface UserStorageCore {
    fun getSessionValue(): UserSessionCore?

    fun setSessionValue(UserSession: UserSessionCore?)
}
