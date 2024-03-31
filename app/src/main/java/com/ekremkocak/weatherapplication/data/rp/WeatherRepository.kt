package com.ekremkocak.weatherapplication.data.rp


import com.ekremkocak.weatherapplication.data.AppApi
import com.ekremkocak.weatherapplication.utils.Constants
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val appApi: AppApi) {

    suspend fun getWeather(country: String) =
        appApi.getCountryWeather(Constants.API_KEY,5,"json",country)
}