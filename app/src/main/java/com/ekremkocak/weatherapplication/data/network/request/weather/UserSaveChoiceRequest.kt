package com.ekremkocak.weatherapplication.data.network.request.weather

data class UserSaveChoiceRequest(
    val isAccept: Boolean,
    val city: String,
)