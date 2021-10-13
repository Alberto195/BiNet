package com.example.binettest.data.di

import androidx.room.Room
import com.example.binettest.data.entry_list.repositories.EntryRepositoryImpl
import com.example.binettest.data.entry_list.storage.UserStorageDB
import com.example.binettest.data.entry_list.storage.db.DatabaseRepositoryImpl
import com.example.binettest.data.entry_list.storage.db.EntryListRoomDB
import com.example.binettest.data.view_entry.storage.db.ViewEntryRoomDB
import com.example.binettest.domain.entry_list.repositories.EntryRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

val viewEntryRoomDaoModule: Module = module {

    // Room Database
    single<ViewEntryRoomDB> {
        Room.databaseBuilder(androidApplication(), ViewEntryRoomDB::class.java, "database")
            .build()
    }

    // Expose WeatherDAO
    single { get<ViewEntryRoomDB>().getRoomDao() }
}

val entryListDataRepositoryModule: Module = module {
    single<EntryRepository> { EntryRepositoryImpl(get(), get(), get(), get()) }

    single<UserStorageDB> { DatabaseRepositoryImpl(get()) }

    // Room Database
    single<EntryListRoomDB> {
        Room.databaseBuilder(androidApplication(), EntryListRoomDB::class.java, "database")
            .build()
    }

    // Expose WeatherDAO
    single { get<EntryListRoomDB>().getRoomDao() }
}
