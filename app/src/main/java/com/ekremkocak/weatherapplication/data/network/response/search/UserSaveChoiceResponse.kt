package com.ekremkocak.weatherapplication.data.network.response.search

data class UserSaveChoiceResponse(
    val errors: List<String>,
    val message: String,
    val succeeded: Boolean
)
