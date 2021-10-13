package com.example.binettest.data.core.storage.sharedprefs

import android.content.Context
import com.example.binettest.data.core.storage.UserStorageCore
import com.example.binettest.data.core.storage.models.UserSessionCore

class SharedPrefUserStorageCore(
        context: Context
) : UserStorageCore {

    private val preferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)

    override fun getSessionValue(): UserSessionCore {
        return UserSessionCore(
                sessionId = preferences.getString(SESSION, "")
        )
    }

    override fun setSessionValue(UserSession: UserSessionCore?) {
        preferences.edit()
                .putString(SESSION, UserSession?.sessionId)
                .apply()
    }

    companion object {
        private const val NAME_PREF = "preference"
        private const val SESSION = "session"
    }

}
