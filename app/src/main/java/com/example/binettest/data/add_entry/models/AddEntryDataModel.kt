package com.example.binettest.data.add_entry.models

import com.google.gson.annotations.SerializedName


data class AddEntryDataModel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: Int
) {
    data class Data(
        @SerializedName("id")
        val id: String
    )
}