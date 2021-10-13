package com.example.binettest.data.core.repositories

import com.example.binettest.data.core.api_service.SessionApi
import com.example.binettest.data.core.mappers.UserSessionMapper
import com.example.binettest.data.core.storage.UserStorageCore
import com.example.binettest.data.core.storage.models.UserSessionCore
import com.example.binettest.domain.core.models.UserSessionId
import com.example.binettest.domain.core.repositories.UserSessionRepository
import io.reactivex.rxjava3.core.Single

class UserSessionRepositoryImpl (
        private val api: SessionApi,
        private val userSessionMapper: UserSessionMapper,
        private val userStorageCore: UserStorageCore
) : UserSessionRepository {

    override fun getNewUserSessionId(): Single<UserSessionId> {
        val call = api.getSession("new_session")

        return userSessionMapper.userSessionDataModelToUserSessionId(call.execute().body())
    }

    override fun getStorageSessionId(): UserSessionId {
        return mapToDomain(userStorageCore.getSessionValue())
    }

    override fun setStorageSessionValue(userSessionId: UserSessionId?) {
        userStorageCore.setSessionValue(mapToStorage(userSessionId))
    }

    private fun mapToDomain(userSession: UserSessionCore?): UserSessionId {
        return UserSessionId(
                sessionId = userSession?.sessionId
        )
    }

    private fun mapToStorage(userSessionId: UserSessionId?): UserSessionCore {
        return UserSessionCore(
                sessionId = userSessionId?.sessionId
        )
    }
}
