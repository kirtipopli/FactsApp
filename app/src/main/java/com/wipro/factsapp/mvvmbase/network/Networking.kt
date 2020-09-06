package com.wipro.factsapp.mvvmbase.network

import android.content.Context
import com.wipro.factsapp.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object Networking {

    const val HEADER_API_KEY = "x-api-key"
    const val HEADER_ACCESS_TOKEN = "x-access-token"
    const val HEADER_USER_ID = "x-user-id"

    private const val NETWORK_CALL_TIMEOUT = 60
    internal lateinit var API_KEY: String

    fun create(
        apiKey: String,
        baseUrl: String,
        cacheDir: File,
        cacheSize: Long,
        context: Context
    ): NetworkService {
        API_KEY = apiKey
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .cache(Cache(cacheDir, cacheSize))
                    .addInterceptor(
                        HttpLoggingInterceptor()
                            .apply {
                                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                                else HttpLoggingInterceptor.Level.NONE
                            })
                    .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .connectTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(JacksonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(NetworkService::class.java)
    }

}