package com.example.binettest.presentation.entry_list.di

import com.example.binettest.domain.core.usecases.GetNewSessionValueUseCase
import com.example.binettest.domain.core.usecases.GetUserSessionIdUseCase
import com.example.binettest.domain.core.usecases.SetUserSessionUseCase
import com.example.binettest.domain.entry_list.usecases.GetEntryListUseCase
import com.example.binettest.domain.entry_list.usecases.SetViewEntryUseCase
import com.example.binettest.presentation.core.viewmodels.MainViewModel
import com.example.binettest.presentation.entry_list.viewmodels.EntryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val entryListViewModelModule: Module = module {
    viewModel { EntryListViewModel(get(), get()) }
}

val entryListUseCasesModule: Module = module {
    factory { GetEntryListUseCase(get()) }

    factory { SetViewEntryUseCase(get()) }
}


val coreViewModelModule: Module = module {
    viewModel { MainViewModel(get(), get(), get()) }
}

val coreUseCasesModule: Module = module {
    factory { GetNewSessionValueUseCase(get()) }

    factory { GetUserSessionIdUseCase(get()) }

    factory { SetUserSessionUseCase(get()) }
}