package com.ekremkocak.weatherapplication.data

import com.ekremkocak.weatherapplication.data.network.response.search.SearchCountryResponse
import com.ekremkocak.weatherapplication.data.network.response.weather.WeatherResponse
import com.ekremkocak.weatherapplication.utils.Constants
import retrofit2.Response
import retrofit2.http.*

interface AppApi {


    @GET(Constants.GET_COUNTRY_WEATHER)
    suspend fun getCountryWeather(
        @Query(Constants.KEY) apiKey: String,
        @Query(Constants.NUM_OF_DAYS) numOfDays: Int,
        @Query(Constants.QUERY_FORMAT) format: String,
        @Query(Constants.QUERY_QUERY) q: String,
    ): Response<WeatherResponse>



    @GET(Constants.GET_SEARCH_COUNTRY)
    suspend fun searchCounrty(
        @Query(Constants.KEY) apiKey: String,
        @Query(Constants.QUERY_FORMAT) format: String,
        @Query(Constants.QUERY_QUERY) q: String,
    ): Response<SearchCountryResponse>
}