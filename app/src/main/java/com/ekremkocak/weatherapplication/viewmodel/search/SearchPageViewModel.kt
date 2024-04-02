package com.ekremkocak.weatherapplication.viewmodel.search

import androidx.constraintlayout.motion.utils.ViewState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekremkocak.weatherapplication.data.network.request.weather.UserSaveChoiceRequest
import com.ekremkocak.weatherapplication.data.network.response.search.Result
import com.ekremkocak.weatherapplication.data.network.response.search.UserSaveChoiceResponse
import com.ekremkocak.weatherapplication.data.rp.SearchRepository
import com.ekremkocak.weatherapplication.data.sealeds.Outcome
import com.ekremkocak.weatherapplication.use_case.search.SaveUserChoicesUSeCase
import com.ekremkocak.weatherapplication.utils.CoroutineExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchPageViewModel @Inject constructor(private val searchRepository: SearchRepository,private val saveUserChoicesUSeCase: SaveUserChoicesUSeCase) :
    ViewModel() {


    private val _userSaveChoiceUiState =
        MutableStateFlow<Outcome<UserSaveChoiceResponse>>(Outcome.EMPTY)
    val userSaveChoiceUiState: StateFlow<Outcome<UserSaveChoiceResponse>>  = _userSaveChoiceUiState

    var mList : MutableLiveData<List<Result>?> = MutableLiveData()
    lateinit var mCityList : MutableSet<String>


    fun searchCountries(key: String) {
        viewModelScope.launch(CoroutineExceptionHandler.handler) {
            searchRepository.searchCountries(key).apply {
                if (this.isSuccessful && this.body()?.searchApi?.result != null) {
                    this.body()?.searchApi?.result.let {
                        this.body()!!.searchApi.result.forEach { it.enable = mCityList.contains(it.areaName[0].value) }
                        mList.postValue( this.body()!!.searchApi.result)
                    }
                } else {
                    mList.postValue(null)
                }
            }
        }
    }


    fun saveUserChoiceServer(requestModel: UserSaveChoiceRequest){//seçimler birdenfazla ekrandan kaldırılabilir use_case
        viewModelScope.launch {
            saveUserChoicesUSeCase.invoke(requestModel).collect {
                when(it){
                    is Outcome.PROGRESS -> _userSaveChoiceUiState.value = Outcome.PROGRESS
                    is Outcome.SUCCESS -> {
                        if (it.data?.succeeded == true){
                            _userSaveChoiceUiState.value =  Outcome.SUCCESS(it.data)
                        }
                    }
                    is Outcome.FAILURE->{
                        _userSaveChoiceUiState.value = Outcome.FAILURE(it.errorMessage)
                    }

                    else -> Outcome.EMPTY
                }
            }
        }
    }

}