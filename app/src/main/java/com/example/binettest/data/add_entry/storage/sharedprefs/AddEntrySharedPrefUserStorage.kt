package com.example.binettest.data.add_entry.storage.sharedprefs

import android.content.Context
import com.example.binettest.data.add_entry.storage.UserStorage
import com.example.binettest.data.add_entry.storage.models.UserSession

class AddEntrySharedPrefUserStorage(
    context: Context
) : UserStorage {

    private val preferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)

    override fun getSessionValue(): UserSession {
        return UserSession(
            sessionId = preferences.getString(SESSION, "")
        )
    }

    companion object {
        private const val NAME_PREF = "preference"
        private const val SESSION = "session"
    }

}