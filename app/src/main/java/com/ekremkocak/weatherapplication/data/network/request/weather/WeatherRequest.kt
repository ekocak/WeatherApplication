package com.ekremkocak.weatherapplication.data.network.request.weather

data class WeatherRequest(
    val country: String,
    val numOfDays: Int=5,
    val key: String="14b1b5b930b1428986c121745242903",
    val format: String="json",
    val q: String="Dublib",

)