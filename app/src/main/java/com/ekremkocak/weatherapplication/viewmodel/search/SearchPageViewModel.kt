package com.ekremkocak.weatherapplication.viewmodel.search

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekremkocak.weatherapplication.data.network.response.search.Result
import com.ekremkocak.weatherapplication.data.network.response.search.SearchCountryResponse
import com.ekremkocak.weatherapplication.data.rp.SearchRepository
import com.ekremkocak.weatherapplication.utils.Constants
import com.ekremkocak.weatherapplication.utils.CoroutineExceptionHandler
import com.ekremkocak.weatherapplication.utils.Prefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchPageViewModel @Inject constructor(private val searchRepository: SearchRepository,val application: Application) :
    ViewModel() {

    var mList : MutableLiveData<List<Result>?>
    lateinit var mCountryList : MutableSet<String>

    init {
        mList = MutableLiveData()
    }


    fun searchCountries(key: String) {
        viewModelScope.launch(CoroutineExceptionHandler.handler) {
            //_weatherUIState.value = DashboardViewModel.ViewState.ShowLoading(true)
            searchRepository.searchCountries(key).apply {
                //_weatherUIState.value = DashboardViewModel.ViewState.ShowLoading(false)


                if (this.isSuccessful && this.body()?.searchApi?.result != null) {
                    this.body()?.searchApi?.result.let {
                        this.body()!!.searchApi.result.forEach { it.enable = mCountryList.contains(it.country[0].value) }
                        mList.postValue( this.body()!!.searchApi.result)
                    }


                } else {
                    mList.postValue(null)
                   // _weatherUIState.value = DashboardViewModel.ViewState.ShowErrorMessage(this.message())
                }
                println("response : "+this.body())
            }
        }
    }



}