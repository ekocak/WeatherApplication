package com.ekremkocak.weatherapplication.data.rp


import com.ekremkocak.weatherapplication.data.AppApi
import com.ekremkocak.weatherapplication.utils.Constants
import javax.inject.Inject

class SearchRepository @Inject constructor(private val appApi: AppApi) {

    suspend fun searchCountries(key: String) =
        appApi.searchCounrty(Constants.API_KEY,"json",key)
}