package com.example.binettest.presentation.entry_list.viewmodels

import android.util.Log
import com.example.binettest.domain.entry_list.models.EntryListModel
import com.example.binettest.domain.entry_list.usecases.GetEntryListUseCase
import com.example.binettest.domain.entry_list.usecases.SetViewEntryUseCase
import com.example.binettest.presentation.base.viewmodel.BaseViewModel
import com.example.binettest.presentation.entry_list.action.EntryListAction
import com.example.binettest.presentation.entry_list.viewstate.EntryListViewState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class EntryListViewModel(
    private val entryListUseCase: GetEntryListUseCase,
    private val setViewEntry: SetViewEntryUseCase
) : BaseViewModel<EntryListAction, EntryListViewState>() {

    companion object {
        const val TAG = "EntryListViewModel"
    }

    private var entry: List<EntryListModel?>? = null

    override fun handleAction(action: EntryListAction) {
        when (action) {
            is EntryListAction.ShowClicked -> {
                getEntryList()
            }
        }
    }

    fun getEntry(): List<EntryListModel?>? {
        return entry
    }

    private fun getEntryList() {
        try {
            disposable.add(
                getObservableEntryList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { models, throwable ->
                        if (!models.isNullOrEmpty()) {
                            entry = models
                            postValue(EntryListViewState.ListLoaded)
                        } else {
                            Log.e(TAG, "onError()", throwable)
                            postValue(EntryListViewState.ListFailed)
                        }
                    }
            )
        } catch (e: Exception) {
            postValue(EntryListViewState.ListFailed)
        }
    }

    fun setViewEntry(entry: EntryListModel?) {
        disposable.add(
            getObservableViewEntry(entry)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {}
        )
    }

    private fun getObservableEntryList(): Single<List<EntryListModel>?> {
        return Single.defer {
            entryListUseCase.execute()
        }
    }

    private fun getObservableViewEntry(entry: EntryListModel?): Completable {
        return Completable.defer {
            Completable.create {
                setViewEntry.execute(entry)
            }
        }
    }
}