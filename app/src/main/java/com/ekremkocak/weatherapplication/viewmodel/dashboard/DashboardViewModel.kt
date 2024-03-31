package com.ekremkocak.weatherapplication.viewmodel.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekremkocak.weatherapplication.data.network.response.weather.WeatherResponse
import com.ekremkocak.weatherapplication.data.rp.WeatherRepository
import com.ekremkocak.weatherapplication.databinding.DashboardSliderBinding
import com.ekremkocak.weatherapplication.utils.CoroutineExceptionHandler

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    sealed class ViewState {
        class ShowLoading(val isShow: Boolean) : ViewState()
        class ShowErrorMessage(val message: String) : ViewState()
        class OnGetted(val response: WeatherResponse) : ViewState()
        object Empty : ViewState()
    }

    private val _weatherUIState = MutableStateFlow<ViewState>(ViewState.Empty)
    val weatherUIState: StateFlow<ViewState> = _weatherUIState



    fun getContryWeather(weatherResponse: WeatherResponse , binding: DashboardSliderBinding) {
        viewModelScope.launch(CoroutineExceptionHandler.handler) {
            _weatherUIState.value = ViewState.ShowLoading(true)
            weatherRepository.getWeather(weatherResponse.country).apply {
                _weatherUIState.value = ViewState.ShowLoading(false)


                if (this.isSuccessful) {
                    _weatherUIState.value = ViewState.OnGetted(this.body()!!)
                    weatherResponse.data = this.body()!!.data
                    binding.weatherResponse = this.body()
                } else {
                    _weatherUIState.value = ViewState.ShowErrorMessage(this.message())
                }
                println("response : "+this.body())
            }
        }
    }
}