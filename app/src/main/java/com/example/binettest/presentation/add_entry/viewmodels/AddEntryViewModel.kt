package com.example.binettest.presentation.add_entry.viewmodels

import com.example.binettest.domain.add_entry.models.AddEntryModel
import com.example.binettest.domain.add_entry.usecases.AddEntryUseCase
import com.example.binettest.presentation.add_entry.actions.AddEntryAction
import com.example.binettest.presentation.add_entry.viewstates.AddEntryViewState
import com.example.binettest.presentation.base.viewmodel.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers


class AddEntryViewModel(
    private val addEntry: AddEntryUseCase
) : BaseViewModel<AddEntryAction, AddEntryViewState>() {

    override fun handleAction(action: AddEntryAction) {
        when (action) {
            is AddEntryAction.SaveClicked -> {
                saveClicked(action)
            }
            is AddEntryAction.CleanClicked -> {
                cleanClicked()
            }
        }
    }

    private fun saveClicked(action: AddEntryAction.SaveClicked) {
        try {
            disposable.add(
                getObservable(action)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        postValue(AddEntryViewState.AddSuccessful)
                    }
            )
        } catch (e: Exception) {
            postValue(AddEntryViewState.AddFailed)
        }
    }

    private fun cleanClicked() {
        try {
            postValue(AddEntryViewState.CleanSuccessful)
        } catch (e: Exception) {
            postValue(AddEntryViewState.CleanFailed)
        }
    }

    private fun getObservable(action: AddEntryAction.SaveClicked): Completable {
        return Completable.defer {
            Completable.create {
                addEntry.execute(
                    AddEntryModel(bodyText = action.body)
                )
            }
        }
    }
}