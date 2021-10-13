package com.example.binettest.presentation.view_entry.viewmodels

import android.util.Log
import com.example.binettest.domain.view_entry.models.EntryDetailsModel
import com.example.binettest.domain.view_entry.usecases.DeleteEntryUseCase
import com.example.binettest.domain.view_entry.usecases.GetEntryDetailsUseCase
import com.example.binettest.presentation.base.viewmodel.BaseViewModel
import com.example.binettest.presentation.entry_list.viewmodels.EntryListViewModel
import com.example.binettest.presentation.view_entry.actions.ViewEntryAction
import com.example.binettest.presentation.view_entry.viewstate.ViewEntryViewState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ViewEntryViewModel(
    private val entryDetailsUseCase: GetEntryDetailsUseCase,
    private val deleteEntryUseCase: DeleteEntryUseCase
) : BaseViewModel<ViewEntryAction, ViewEntryViewState>() {

    override fun handleAction(action: ViewEntryAction) {}

    private var entry: EntryDetailsModel? = null

    fun getWholeEntry() {
        try {
            disposable.add(
                getObservableEntry()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { loadedEntry, throwable ->
                        if (loadedEntry != null) {
                            entry = loadedEntry
                            postValue(ViewEntryViewState.EntryLoaded)
                        } else {
                            Log.e(EntryListViewModel.TAG, "onError()", throwable)
                            postValue(ViewEntryViewState.EntryFailed)
                        }
                    }
            )
        } catch (e: Exception) {
            postValue(ViewEntryViewState.EntryFailed)
        }
    }

    fun getEntry(): EntryDetailsModel? {
        return entry
    }

    fun deleteEntry() {
        try {
            disposable.add(
                getObservableDeleteEntry()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { }
            )
        } catch (e: Exception) {
            postValue(ViewEntryViewState.EntryFailed)
        }
    }

    private fun getObservableEntry(): Single<EntryDetailsModel> {
        return Single.defer {
            entryDetailsUseCase.execute()
        }
    }

    private fun getObservableDeleteEntry(): Completable {
        return Completable.defer {
            Completable.create {
                deleteEntryUseCase.execute()
            }
        }
    }
}