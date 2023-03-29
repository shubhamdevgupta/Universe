package com.example.alankituniverse.di


import com.example.alankituniverse.BuildConfig
import com.example.alankituniverse.data.service.MainService
import com.example.alankituniverse.util.api.ApiInterceptor
import com.example.alankituniverse.util.helper.AppConstants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideBaseUrl() = AppConstants.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(apiInterceptor: ApiInterceptor) = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(apiInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS).build()
    } else OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build()

    @Singleton
    @Provides
    fun provideApiClient(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().serializeNulls().setLenient().create()
            )
        ).client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideMainService(retrofit: Retrofit): MainService =
        retrofit.create(MainService::class.java)

}