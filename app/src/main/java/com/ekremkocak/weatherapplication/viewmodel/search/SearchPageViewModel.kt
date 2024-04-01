package com.ekremkocak.weatherapplication.viewmodel.search

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekremkocak.weatherapplication.data.network.response.search.Result
import com.ekremkocak.weatherapplication.data.rp.SearchRepository
import com.ekremkocak.weatherapplication.utils.CoroutineExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchPageViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

    var mList : MutableLiveData<List<Result>?>
    lateinit var mCityList : MutableSet<String>

    init {
        mList = MutableLiveData()
    }


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



}