package com.example.binettest.presentation.add_entry.actions

sealed class AddEntryAction {
    data class SaveClicked(
        val body: String
    ): AddEntryAction()

    object CleanClicked : AddEntryAction()
}