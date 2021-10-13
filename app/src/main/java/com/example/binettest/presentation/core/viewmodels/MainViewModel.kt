package com.example.binettest.presentation.core.viewmodels

import android.util.Log
import com.example.binettest.domain.core.models.UserSessionId
import com.example.binettest.domain.core.usecases.GetNewSessionValueUseCase
import com.example.binettest.domain.core.usecases.GetUserSessionIdUseCase
import com.example.binettest.domain.core.usecases.SetUserSessionUseCase
import com.example.binettest.presentation.base.viewmodel.BaseViewModel
import com.example.binettest.presentation.base.viewstate.BaseViewState
import com.example.binettest.presentation.core.actions.ActivityAction
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(
    private val getUserSessionIdUseCase: GetUserSessionIdUseCase,
    private val setUserSessionUseCase: SetUserSessionUseCase,
    private val getSessionValueUseCase: GetNewSessionValueUseCase
) : BaseViewModel<BaseViewState<*>, ActivityAction>() {

    companion object {
        private const val TAG = "MainViewModel"
    }

    fun getSession(): UserSessionId? {
        return getUserSessionIdUseCase.execute()
    }

    fun setSession() {
        try {
            disposable.add(
                getObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { success, throwable ->
                        if (success != null) setUserSessionUseCase.execute(success)
                        else Log.e(TAG, "onError()", throwable)
                    }
            )
        } catch (e: Exception) {
            Log.e("DAMN", e.toString())
        }
    }

    private fun getObservable(): Single<UserSessionId> {
        return getSessionValueUseCase.execute()
    }

    override fun handleAction(action: BaseViewState<*>) {}
}