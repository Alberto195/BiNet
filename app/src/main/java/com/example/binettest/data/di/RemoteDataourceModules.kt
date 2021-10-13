package com.example.binettest.data.di

import android.annotation.SuppressLint
import com.example.binettest.data.add_entry.api_service.AddApi
import com.example.binettest.data.core.api_service.SessionApi
import com.example.binettest.data.entry_list.api_service.EntryApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

private const val BASE_URL = "https://bnet.i-partner.ru/"

val entryListDataApiModule: Module = module {
    factory { provideOkHttpClient() }
    single<EntryApi> { createWebService(get(), BASE_URL, get()) }
    single<SessionApi> { createWebService(get(), BASE_URL, get()) }
    factory { provideGson() }
}

val addEntryApiModule: Module = module {
    single<AddApi> { createWebService(get(), BASE_URL, get()) }
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String, gson: Gson): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    return retrofit.create(T::class.java)
}

fun provideGson(): Gson = GsonBuilder().setLenient().create()

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
