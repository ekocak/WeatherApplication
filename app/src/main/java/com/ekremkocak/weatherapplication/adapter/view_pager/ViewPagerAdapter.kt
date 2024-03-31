package com.ekremkocak.weatherapplication.adapter.view_pager

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import com.ekremkocak.weatherapplication.MainActivity
import com.ekremkocak.weatherapplication.data.network.response.weather.WeatherResponse
import com.ekremkocak.weatherapplication.databinding.DashboardSliderBinding
import com.ekremkocak.weatherapplication.view.dashboard.DashboardFragment


class ViewPagerAdapter(private val context: Fragment) : PagerAdapter() {

    val countryBindingList = mutableListOf<DashboardSliderBinding>()
    val countryArray = mutableListOf<WeatherResponse>()

    init {
        countryArray.add(WeatherResponse("Turkey",null,false))
        countryArray.add(WeatherResponse("Dublin",null,false))
        countryArray.add(WeatherResponse("Dublin",null,false))
        countryArray.add(WeatherResponse("Dublin",null,false))
        countryArray.add(WeatherResponse("Dublin",null,false))
        countryArray.add(WeatherResponse("Dublin",null,false))

    }

    override fun getCount(): Int {
        return countryArray.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = DashboardSliderBinding.inflate(LayoutInflater.from(context.requireContext()), container, false)
        binding.weatherResponse = countryArray[position]

            countryBindingList.add(binding)
            (context as DashboardFragment).getCountryWeather(position)


        container.addView(binding.root)
        context.requireActivity() as MainActivity
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}