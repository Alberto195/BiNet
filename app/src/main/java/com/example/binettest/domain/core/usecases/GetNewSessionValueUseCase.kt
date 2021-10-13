package com.example.binettest.domain.core.usecases

import com.example.binettest.domain.core.models.UserSessionId
import com.example.binettest.domain.core.repositories.UserSessionRepository
import io.reactivex.rxjava3.core.Single

class GetNewSessionValueUseCase(
        private val repository: UserSessionRepository
) {

    fun execute(): Single<UserSessionId> {
        return repository.getNewUserSessionId()
    }
}
