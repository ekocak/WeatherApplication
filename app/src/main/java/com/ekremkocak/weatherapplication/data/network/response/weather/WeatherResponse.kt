package com.ekremkocak.weatherapplication.data.network.response.weather

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class WeatherResponse(
    var country: String,
    var data: DataResponse?,
    val succeeded: Boolean,
):Serializable

data class DataResponse(
    val request: ArrayList<RequestResponse>,
    val weather: ArrayList<Weather>,
    val currentCondition: ArrayList<CurrentCondition>,
):Serializable

data class RequestResponse(
    @SerializedName("type")
    val type: String, val query: String):Serializable
data class Weather(
    val date: String,
    val maxtempC: String,
    val mintempC: String,
):Serializable


data class CurrentCondition(
    val temp_C: String,
    val weatherDesc: String,
    val windspeedKmph: String,
    val winddirDegree: String,
    val FeelsLikeC: String,
)