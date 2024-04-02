package com.ekremkocak.weatherapplication.use_case.search

import com.ekremkocak.weatherapplication.data.network.request.weather.UserSaveChoiceRequest
import com.ekremkocak.weatherapplication.data.network.response.search.UserSaveChoiceResponse
import com.ekremkocak.weatherapplication.data.sealeds.Outcome
import kotlinx.coroutines.flow.Flow

interface SaveUserChoicesUSeCase {
    operator fun invoke(request: UserSaveChoiceRequest): Flow<Outcome<UserSaveChoiceResponse>>
}