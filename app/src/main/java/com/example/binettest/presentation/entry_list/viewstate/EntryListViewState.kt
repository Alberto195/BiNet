package com.example.binettest.presentation.entry_list.viewstate

sealed class EntryListViewState {
    object ListLoaded: EntryListViewState()
    object ListFailed: EntryListViewState()
}