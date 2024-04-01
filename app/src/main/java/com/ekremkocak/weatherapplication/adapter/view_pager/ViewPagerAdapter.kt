package com.ekremkocak.weatherapplication.adapter.view_pager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.ekremkocak.weatherapplication.MainActivity
import com.ekremkocak.weatherapplication.adapter.dashboard.DashboardWeatherAdapter
import com.ekremkocak.weatherapplication.data.network.response.weather.Weather
import com.ekremkocak.weatherapplication.data.network.response.weather.WeatherResponse
import com.ekremkocak.weatherapplication.databinding.DashboardSliderBinding
import com.ekremkocak.weatherapplication.utils.Constants
import com.ekremkocak.weatherapplication.utils.Prefs
import com.ekremkocak.weatherapplication.view.dashboard.DashboardFragment


class ViewPagerAdapter(private val fragment: Fragment) : PagerAdapter() {

    val countryArray = mutableListOf<WeatherResponse>()

    init {
        val cityList = Prefs.getStringListSharedPreferences(fragment.requireContext(), Constants.CITY)
        cityList?.forEach {
            println("init ${it}")
            countryArray.add(WeatherResponse(it,null,false))
        }


    }

    override fun getCount(): Int {
        return countryArray.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val binding = DashboardSliderBinding.inflate(LayoutInflater.from(fragment.requireContext()), container, false)
        binding.weatherResponse = countryArray[position]


        println("daneme : kaç")

        (fragment as DashboardFragment).getCountryWeather(position,binding)


        container.addView(binding.root)
        fragment.requireActivity() as MainActivity//non required
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
    fun setUpRecyclerView(result: ArrayList<Weather>,recyclerView: RecyclerView){
        val rvAdapter = DashboardWeatherAdapter(result)
        recyclerView.apply {
            println("adapter")
            layoutManager =  LinearLayoutManager(fragment.requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = rvAdapter
        }




    }
}