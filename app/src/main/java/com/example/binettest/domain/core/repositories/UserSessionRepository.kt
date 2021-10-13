package com.example.binettest.domain.core.repositories

import com.example.binettest.domain.core.models.UserSessionId
import io.reactivex.rxjava3.core.Single

interface UserSessionRepository {

    fun getNewUserSessionId(): Single<UserSessionId>

    fun getStorageSessionId(): UserSessionId?

    fun setStorageSessionValue(userSessionId: UserSessionId?)

}