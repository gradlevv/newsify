package com.gradlevv.core.di

import android.content.Context
import android.net.ConnectivityManager
import kotlinx.serialization.json.Json
import com.gradlevv.core.util.Constants.API_KEY
import com.gradlevv.core.util.Constants.BASE_URL_API
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CoreNetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitClientApi(
        @Named(BASE_URL_API) baseUrl: String,
        client: Lazy<OkHttpClient>,
        factory: Converter.Factory
    ): Retrofit {

        return Retrofit.Builder()
            .callFactory(Call.Factory { request: Request ->
                return@Factory client.get().newCall(request)
            })
            .baseUrl(baseUrl)
            .addConverterFactory(factory)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor,
        headersInterceptor: Interceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(headersInterceptor)
            .addInterceptor(interceptor)
            .retryOnConnectionFailure(true)
            .followRedirects(false)
            .followRedirects(false)
            .connectTimeout(60, TimeUnit.SECONDS)
            .callTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideGsonConverterFactory(): Converter.Factory {
        val networkJson = Json { ignoreUnknownKeys = true }
        return networkJson.asConverterFactory("application/json".toMediaType())
    }


    @Provides
    fun provideConnectivityManager(context: Context): ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Named(BASE_URL_API)
    @Provides
    fun provideBaseApiUrl() = "https://newsapi.org/"

    @Provides
    @Singleton
    fun provideRequestHeadersInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-api-key", API_KEY)

            return@Interceptor chain.proceed(request.build())
        }
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
}