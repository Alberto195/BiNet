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

val viewEntryViewModelModule: Module = module {
    viewModel { ViewEntryViewModel(get(), get()) }
}

val viewEntryUseCaseModule: Module = module {
    factory { GetEntryDetailsUseCase(get()) }

    factory { DeleteEntryUseCase(get()) }
}

val addEntryViewModelModule: Module = module {
    viewModel { AddEntryViewModel(get()) }
}

val addEntryUseCaseModule: Module = module {
    factory { AddEntryUseCase(get()) }
}