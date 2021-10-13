package com.example.binettest.presentation.di

import com.example.binettest.domain.add_entry.usecases.AddEntryUseCase
import com.example.binettest.domain.core.usecases.GetNewSessionValueUseCase
import com.example.binettest.domain.core.usecases.GetUserSessionIdUseCase
import com.example.binettest.domain.core.usecases.SetUserSessionUseCase
import com.example.binettest.domain.entry_list.usecases.GetEntryListUseCase
import com.example.binettest.domain.entry_list.usecases.SetViewEntryUseCase
import com.example.binettest.domain.view_entry.usecases.DeleteEntryUseCase
import com.example.binettest.domain.view_entry.usecases.GetEntryDetailsUseCase
import com.example.binettest.presentation.add_entry.viewmodels.AddEntryViewModel
import com.example.binettest.presentation.core.viewmodels.MainViewModel
import com.example.binettest.presentation.entry_list.viewmodels.EntryListViewModel
import com.example.binettest.presentation.view_entry.viewmodels.ViewEntryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val entryListViewModelModule: Module = module {
    viewModel { EntryListViewModel(get(), get()) }
}

val coreViewModelModule: Module = module {
    viewModel { MainViewModel(get(), get(), get()) }
}

val viewEntryViewModelModule: Module = module {
    viewModel { ViewEntryViewModel(get(), get()) }
}

val addEntryViewModelModule: Module = module {
    viewModel { AddEntryViewModel(get()) }
}

val entryListUseCasesModule: Module = module {
    single { GetEntryListUseCase(get()) }

    single { SetViewEntryUseCase(get()) }
}

val coreUseCasesModule: Module = module {
    single { GetNewSessionValueUseCase(get()) }

    single { GetUserSessionIdUseCase(get()) }

    single { SetUserSessionUseCase(get()) }
}


val viewEntryUseCaseModule: Module = module {
    single { GetEntryDetailsUseCase(get()) }

    single { DeleteEntryUseCase(get()) }
}

val addEntryUseCaseModule: Module = module {
    single { AddEntryUseCase(get()) }
}