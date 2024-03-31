package com.ekremkocak.weatherapplication.utils

import kotlinx.coroutines.CoroutineExceptionHandler

object CoroutineExceptionHandler {
    val handler = CoroutineExceptionHandler { context, throwable ->
        println("exception: ${throwable.message}")
    }
}