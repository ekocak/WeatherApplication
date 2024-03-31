package com.ekremkocak.weatherapplication.data

import com.ekremkocak.weatherapplication.data.network.response.search.SearchCountryResponse
import com.ekremkocak.weatherapplication.data.network.response.weather.WeatherResponse
import com.ekremkocak.weatherapplication.utils.Constants
import retrofit2.Response
import retrofit2.http.*

interface AppApi {


    @GET(Constants.COUNTRY_WEATHER_GET)
    suspend fun getCountryWeather(
        @Query(Constants.KEY) apiKey: String,
        @Query(Constants.NUM_OF_DAYS) numOfDays: Int,
        @Query(Constants.FORMAT) format: String,
        @Query(Constants.QUERY) q: String,
    ): Response<WeatherResponse>



    @GET(Constants.SEARCH_COUNTRY)
    suspend fun searchCounrty(
        @Query(Constants.KEY) apiKey: String,
        @Query(Constants.FORMAT) format: String,
        @Query(Constants.QUERY) q: String,
    ): Response<SearchCountryResponse>
}