package com.example.binettest.data.di

import android.annotation.SuppressLint
import com.example.binettest.data.add_entry.api_service.AddApi
import com.example.binettest.data.add_entry.repositories.AddEntryRepositoryImpl
import com.example.binettest.data.add_entry.storage.UserStorage
import com.example.binettest.data.add_entry.storage.sharedprefs.AddEntrySharedPrefUserStorage
import com.example.binettest.data.core.api_service.SessionApi
import com.example.binettest.data.core.mappers.UserSessionMapper
import com.example.binettest.data.core.repositories.UserSessionRepositoryImpl
import com.example.binettest.data.core.storage.UserStorageCore
import com.example.binettest.data.core.storage.sharedprefs.SharedPrefUserStorageCore
import com.example.binettest.data.entry_list.api_service.EntryApi
import com.example.binettest.data.entry_list.mappers.EntryListMapper
import com.example.binettest.data.entry_list.repositories.EntryRepositoryImpl
import com.example.binettest.data.entry_list.storage.UserStorageDB
import com.example.binettest.data.entry_list.storage.UserStorageSharedPrefs
import com.example.binettest.data.entry_list.storage.db.DatabaseRepositoryImpl
import com.example.binettest.data.entry_list.storage.db.EntryListRoomDB
import com.example.binettest.data.entry_list.storage.sharedprefs.SharedPrefUserStorage
import com.example.binettest.data.view_entry.repositories.ViewEntryRepositoryImpl
import com.example.binettest.data.view_entry.storage.UserStorageEntry
import com.example.binettest.data.view_entry.storage.db.DatabaseEntry
import com.example.binettest.data.view_entry.storage.db.ViewEntryRoomDB
import com.example.binettest.domain.add_entry.repositories.AddEntryRepository
import com.example.binettest.domain.core.repositories.UserSessionRepository
import com.example.binettest.domain.entry_list.repositories.EntryRepository
import com.example.binettest.domain.view_entry.repositories.ViewEntryRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


val entryListDataApiModule: Module = module {
    factory { provideOkHttpClient() }
    factory { provideEntryApi(get()) }
    factory { provideSessionApi(get()) }
    single { provideRetrofit(get(), get()) }
    factory { provideGson() }
}
private const val BASE_URL = "https://bnet.i-partner.ru/"

fun provideEntryApi(retrofit: Retrofit): EntryApi = retrofit.create(EntryApi::class.java)

fun provideSessionApi(retrofit: Retrofit): SessionApi = retrofit.create(SessionApi::class.java)

fun provideGson(): Gson = GsonBuilder().setLenient().create()

fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
}

fun provideOkHttpClient(): OkHttpClient {
    return try {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts = arrayOf<TrustManager>(
            object : X509TrustManager {
                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        )

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
        val builder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        builder.interceptors().add(interceptor)

        builder.addInterceptor {
            val original = it.request()
            val request = original.newBuilder()
                .header("token", "POgDQwE-gm-mugzQ8M")
                .method(original.method, original.body)
                .build()
            it.proceed(request)
        }
        builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        builder.hostnameVerifier { _, _ -> true }
        builder.build()
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}

val entryListDataRepositoryModule: Module = module {
    single<EntryRepository> { EntryRepositoryImpl(get(), get(), get(), get()) }

    single<EntryListRoomDB> { EntryListRoomDB.getInstance(androidContext()) }

    single { EntryListRoomDB.getInstance(androidContext()).getRoomDao() }

    single<UserStorageDB> { DatabaseRepositoryImpl(get()) }
}

val entryListDataMapperModule: Module = module {
    single { EntryListMapper() }
}

val entryListStorageModule: Module = module {
    single<UserStorageSharedPrefs> { SharedPrefUserStorage(androidContext()) }
}

val coreRepositoryModule: Module = module {
    single<UserSessionRepository> { UserSessionRepositoryImpl(get(), get(), get()) }

}

val coreDataMapperModule: Module = module {
    single { UserSessionMapper() }
}

val coreStorageModule: Module = module {
    single<UserStorageCore> { SharedPrefUserStorageCore(androidContext()) }
}


val viewEntryRepositoryModule: Module = module {
    single<ViewEntryRepository> { ViewEntryRepositoryImpl(get()) }

    single<UserStorageEntry> { DatabaseEntry(get()) }
}

val viewEntryRoomDaoModule: Module = module {

    single<ViewEntryRoomDB> { ViewEntryRoomDB.getInstance(androidContext()) }

    single { ViewEntryRoomDB.getInstance(androidContext()).getRoomDao() }
}

val addEntryRepositoryModule: Module = module {
    single<AddEntryRepository> { AddEntryRepositoryImpl(get(), get()) }

    single<UserStorage> { AddEntrySharedPrefUserStorage(androidContext()) }
}

val addEntryApiModule: Module = module {
    factory { provideAddEntryApi(get()) }
}

fun provideAddEntryApi(retrofit: Retrofit): AddApi = retrofit.create(AddApi::class.java)
