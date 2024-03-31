package com.ekremkocak.weatherapplication.adapter.listeners

import com.ekremkocak.weatherapplication.data.network.response.search.Result
import com.ekremkocak.weatherapplication.data.network.response.search.SearchCountryResponse

interface CountryItemClickListener {
    fun onClick(result: Result)
}