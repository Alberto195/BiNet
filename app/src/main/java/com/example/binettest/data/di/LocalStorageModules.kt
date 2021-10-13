package com.example.binettest.data.di

import com.example.binettest.data.add_entry.storage.UserStorage
import com.example.binettest.data.add_entry.storage.sharedprefs.AddEntrySharedPrefUserStorage
import com.example.binettest.data.core.storage.UserStorageCore
import com.example.binettest.data.core.storage.sharedprefs.SharedPrefUserStorageCore
import com.example.binettest.data.entry_list.storage.UserStorageSharedPrefs
import com.example.binettest.data.entry_list.storage.sharedprefs.SharedPrefUserStorage
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val entryListStorageModule: Module = module {
    single<UserStorageSharedPrefs> { SharedPrefUserStorage(androidContext()) }
}

val coreStorageModule: Module = module {
    single<UserStorageCore> { SharedPrefUserStorageCore(androidContext()) }
}

val addEntryStorageModule: Module = module {
    single<UserStorage> { AddEntrySharedPrefUserStorage(androidContext()) }
}
