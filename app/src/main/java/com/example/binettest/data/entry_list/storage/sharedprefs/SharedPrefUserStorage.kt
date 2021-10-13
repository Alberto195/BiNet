package com.example.binettest.data.entry_list.storage.sharedprefs

import android.content.Context
import com.example.binettest.data.entry_list.storage.UserStorageSharedPrefs
import com.example.binettest.data.entry_list.storage.models.UserSession

class SharedPrefUserStorage(
    context: Context
) : UserStorageSharedPrefs {

    private val preferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)

    override fun getSessionValue(): UserSession {
        return UserSession(
                sessionId = preferences.getString (SESSION, "")
        )
    }

    companion object {
        private const val NAME_PREF = "preference"
        private const val SESSION = "session"
    }

}
