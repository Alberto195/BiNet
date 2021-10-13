package com.example.binettest.data.entry_list.models

import com.google.gson.annotations.SerializedName

data class EntryDataListModel(
        @SerializedName("data")
    val `data`: List<List<Data>>?,
        @SerializedName("status")
    val status: Int
) {
    data class Data(
        @SerializedName("body")
        val body: String,
        @SerializedName("da")
        val da: String,
        @SerializedName("dm")
        val dm: String,
        @SerializedName("id")
        val id: String
    )
}