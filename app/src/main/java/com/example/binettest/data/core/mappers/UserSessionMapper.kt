package com.example.binettest.data.core.mappers

import com.example.binettest.data.core.models.UserSessionDataModel
import com.example.binettest.domain.core.models.UserSessionId
import io.reactivex.rxjava3.core.Single

class UserSessionMapper {

    fun userSessionDataModelToUserSessionId(
        sessionData: UserSessionDataModel?
    ): Single<UserSessionId> {
        return Single.just(
            UserSessionId(
                sessionId = sessionData?.data?.session
            )
        )
    }

}