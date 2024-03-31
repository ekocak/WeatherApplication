package com.ekremkocak.weatherapplication.di

import android.content.Context
import android.util.Log
import com.ekremkocak.weatherapplication.data.AppApi
import com.ekremkocak.weatherapplication.data.rp.SearchRepository
import com.ekremkocak.weatherapplication.data.rp.WeatherRepository
import com.ekremkocak.weatherapplication.utils.Constants

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesWeatherRepository(appApi: AppApi): WeatherRepository {
        return WeatherRepository(appApi)
    }

    @Provides
    @Singleton
    fun providesSearchRepository(appApi: AppApi): SearchRepository {
        return SearchRepository(appApi)
    }

    @Provides
    fun providesAppApi(): AppApi {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(AppApi::class.java)
    }



}