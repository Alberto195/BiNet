package com.example.binettest.data.add_entry.api_service

import com.example.binettest.data.add_entry.models.AddEntryDataModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AddApi {

    @FormUrlEncoded
    @POST("testAPI/")
    fun addEntry (
            @Field("a") a: String?,
            @Field("session") session: String?,
            @Field("body") body: String?
    ): Call<AddEntryDataModel?>

}