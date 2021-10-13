package com.example.binettest.presentation.add_entry.viewstates
sealed class AddEntryViewState {
    object AddSuccessful: AddEntryViewState()
    object AddFailed: AddEntryViewState()
    object CleanSuccessful: AddEntryViewState()
    object CleanFailed: AddEntryViewState()
}