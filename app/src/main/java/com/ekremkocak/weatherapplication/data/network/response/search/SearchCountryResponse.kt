package com.ekremkocak.weatherapplication.data.network.response.search

import com.google.gson.annotations.SerializedName

data class SearchCountryResponse(
    @SerializedName("search_api")
    val searchApi: SearchApi )

data class SearchApi(val result: List<Result>)
data class Result(
    val country: List<Country>,
    val areaName: List<AreaName>,
    val region: List<Region>,
    var enable: Boolean,
)
data class Country(val value: String)
data class AreaName(val value: String)
data class Region(val value: String)