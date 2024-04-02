package com.ekremkocak.weatherapplication.use_case.search

import com.ekremkocak.weatherapplication.data.network.request.weather.UserSaveChoiceRequest
import com.ekremkocak.weatherapplication.data.network.response.search.UserSaveChoiceResponse
import com.ekremkocak.weatherapplication.data.rp.savechoicerejection.UserSaveChoiceRejectionRepository
import com.ekremkocak.weatherapplication.data.sealeds.Outcome
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveUserChoicesUseCaseImpl @Inject constructor(private val repo: UserSaveChoiceRejectionRepository) :
    SaveUserChoicesUSeCase {
    override fun invoke(request: UserSaveChoiceRequest): Flow<Outcome<UserSaveChoiceResponse>> =
        flow {
            emit(Outcome.PROGRESS)
            when (val response = repo.approveRejectInvitation(request)) {
                is Outcome.FAILURE -> emit(response)
                is Outcome.SUCCESS -> {
                    emit(Outcome.SUCCESS(response.data))
                }
                else -> Unit
            }
        }
}