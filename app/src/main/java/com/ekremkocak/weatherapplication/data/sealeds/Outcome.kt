package com.ekremkocak.weatherapplication.data.sealeds

sealed class Outcome<out T> {
    object EMPTY : Outcome<Nothing>()
    object PROGRESS : Outcome<Nothing>()
    data class SUCCESS<out T>(val data: T?) : Outcome<T>()
    data class FAILURE(val errorMessage: String, val code: Int? = -1) : Outcome<Nothing>()
}
