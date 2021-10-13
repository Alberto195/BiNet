package com.example.binettest.data.core.models

import com.google.gson.annotations.SerializedName

data class UserSessionDataModel(
        @SerializedName("data")
        val `data`: Data,
        @SerializedName("status")
        val status: Int
) {
    data class Data(
            @SerializedName("session")
            val session: String
    )
}