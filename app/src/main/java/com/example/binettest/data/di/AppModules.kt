package com.example.binettest.data.di

import androidx.room.Room
import com.example.binettest.data.add_entry.api_service.AddApi
import com.example.binettest.data.add_entry.repositories.AddEntryRepositoryImpl
import com.example.binettest.data.add_entry.storage.UserStorage
import com.example.binettest.data.add_entry.storage.sharedprefs.AddEntrySharedPrefUserStorage
import com.example.binettest.data.core.mappers.UserSessionMapper
import com.example.binettest.data.core.repositories.UserSessionRepositoryImpl
import com.example.binettest.data.core.storage.UserStorageCore
import com.example.binettest.data.core.storage.sharedprefs.SharedPrefUserStorageCore
import com.example.binettest.data.entry_list.mappers.EntryListMapper
import com.example.binettest.data.entry_list.repositories.EntryRepositoryImpl
import com.example.binettest.data.entry_list.storage.UserStorageDB
import com.example.binettest.data.entry_list.storage.UserStorageSharedPrefs
import com.example.binettest.data.entry_list.storage.db.DatabaseRepositoryImpl
import com.example.binettest.data.entry_list.storage.db.EntryListRoomDB
import com.example.binettest.data.entry_list.storage.sharedprefs.SharedPrefUserStorage
import com.example.binettest.data.view_entry.repositories.ViewEntryRepositoryImpl
import com.example.binettest.data.view_entry.storage.UserStorageEntry
import com.example.binettest.data.view_entry.storage.db.DatabaseEntry
import com.example.binettest.data.view_entry.storage.db.ViewEntryRoomDB
import com.example.binettest.domain.add_entry.repositories.AddEntryRepository
import com.example.binettest.domain.core.repositories.UserSessionRepository
import com.example.binettest.domain.entry_list.repositories.EntryRepository
import com.example.binettest.domain.view_entry.repositories.ViewEntryRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module


val entryListDataMapperModule: Module = module {
    single { EntryListMapper() }
}

val coreRepositoryModule: Module = module {
    single<UserSessionRepository> { UserSessionRepositoryImpl(get(), get(), get()) }

}

val coreDataMapperModule: Module = module {
    single { UserSessionMapper() }
}

val viewEntryRepositoryModule: Module = module {
    single<ViewEntryRepository> { ViewEntryRepositoryImpl(get()) }

    single<UserStorageEntry> { DatabaseEntry(get()) }
}

val addEntryRepositoryModule: Module = module {
    single<AddEntryRepository> { AddEntryRepositoryImpl(get(), get()) }

}
