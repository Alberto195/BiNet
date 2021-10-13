package com.example.binettest.presentation.core

import android.app.Application
import com.example.binettest.data.di.*
import com.example.binettest.presentation.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            androidFileProperties()
            modules(
                listOf(
                    coreRepositoryModule,
                    coreDataMapperModule,
                    coreViewModelModule,
                    coreUseCasesModule,
                    coreStorageModule,

                    entryListViewModelModule,
                    entryListUseCasesModule,
                    entryListDataApiModule,
                    entryListDataMapperModule,
                    entryListDataRepositoryModule,
                    entryListStorageModule,

                    viewEntryViewModelModule,
                    viewEntryUseCaseModule,
                    viewEntryRepositoryModule,
                    viewEntryRoomDaoModule,

                    addEntryRepositoryModule,
                    addEntryApiModule,
                    addEntryViewModelModule,
                    addEntryUseCaseModule,
                )
            )
        }

    }
}