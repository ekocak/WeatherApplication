package com.ekremkocak.weatherapplication.viewmodel.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekremkocak.weatherapplication.data.network.response.weather.WeatherResponse
import com.ekremkocak.weatherapplication.data.rp.WeatherRepository
import com.ekremkocak.weatherapplication.databinding.DashboardSliderBinding
import com.ekremkocak.weatherapplication.utils.CoroutineExceptionHandler
import com.ekremkocak.weatherapplication.view.dashboard.DashboardFragment

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    private lateinit var job: Job
    sealed class ViewState {
        class ShowLoading(val isShow: Boolean) : ViewState()
        class ShowErrorMessage(val message: String) : ViewState()
        class OnLoaded(val response: WeatherResponse) : ViewState()
        object Empty : ViewState()
    }

    private val _weatherUIState = MutableStateFlow<ViewState>(ViewState.Empty)
    val weatherUIState: StateFlow<ViewState> = _weatherUIState



    fun loadContryWeather(weatherResponse: WeatherResponse, binding: DashboardSliderBinding, dashboardFragment: DashboardFragment) {
        job = viewModelScope.launch(CoroutineExceptionHandler.handler) {
            _weatherUIState.value = ViewState.ShowLoading(true)
            weatherRepository.getWeather(weatherResponse.country).apply {
                _weatherUIState.value = ViewState.ShowLoading(false)
                if (this.isSuccessful) {
                    _weatherUIState.value = ViewState.OnLoaded(this.body()!!)
                    weatherResponse.data = this.body()!!.data
                    binding.weatherResponse = weatherResponse
                    dashboardFragment.viewPagerAdapter.setUpRecyclerView(weatherResponse.data!!.weather,binding.rvDashboardDailyWeathers)

                } else {
                    _weatherUIState.value = ViewState.ShowErrorMessage(this.message())
                }
            }

        }
    }
    override fun onCleared() {
        if (this::job.isInitialized) {
            job.cancel()
        }
        super.onCleared()
    }
}