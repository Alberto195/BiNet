package com.example.binettest.data.entry_list.api_service

import com.example.binettest.data.entry_list.models.EntryDataListModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface EntryApi {

    @FormUrlEncoded
    @POST("testAPI/")
    fun getEntries(
            @Field("a") a: String?,
            @Field("session") session: String?
    ): Call<EntryDataListModel?>

}
