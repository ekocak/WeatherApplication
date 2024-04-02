package com.ekremkocak.weatherapplication.data.rp.savechoicerejection

import com.ekremkocak.weatherapplication.data.network.request.weather.UserSaveChoiceRequest
import com.ekremkocak.weatherapplication.data.network.response.search.UserSaveChoiceResponse
import com.ekremkocak.weatherapplication.data.sealeds.Outcome


interface UserSaveChoiceRejectionRepository {
    suspend fun approveRejectInvitation(body : UserSaveChoiceRequest) : Outcome<UserSaveChoiceResponse>
}