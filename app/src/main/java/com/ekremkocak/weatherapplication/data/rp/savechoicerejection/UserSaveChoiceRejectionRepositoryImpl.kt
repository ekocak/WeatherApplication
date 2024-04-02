package com.sporyap.sporyap.data.rp.event.acceptancerejection

import com.ekremkocak.weatherapplication.data.AppApi
import com.ekremkocak.weatherapplication.data.network.request.weather.UserSaveChoiceRequest
import com.ekremkocak.weatherapplication.data.network.response.search.UserSaveChoiceResponse
import com.ekremkocak.weatherapplication.data.rp.savechoicerejection.UserSaveChoiceRejectionRepository
import com.ekremkocak.weatherapplication.data.sealeds.Outcome
import javax.inject.Inject

class UserSaveChoiceRejectionRepositoryImpl @Inject constructor(private val api: AppApi) :
    UserSaveChoiceRejectionRepository {

    override suspend fun approveRejectInvitation(body: UserSaveChoiceRequest): Outcome<UserSaveChoiceResponse> =
        try {
            Outcome.SUCCESS(api.eventAcceptanceRejection(body))
        } catch (e: Exception) {
            Outcome.FAILURE(e.message.toString())
        }

}