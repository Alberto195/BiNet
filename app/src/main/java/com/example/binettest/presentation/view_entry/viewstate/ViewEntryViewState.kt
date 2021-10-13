package com.example.binettest.presentation.view_entry.viewstate

sealed class ViewEntryViewState {
    object EntryLoaded: ViewEntryViewState()
    object EntryFailed: ViewEntryViewState()
}