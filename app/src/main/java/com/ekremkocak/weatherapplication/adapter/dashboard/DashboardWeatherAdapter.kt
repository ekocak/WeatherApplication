package com.ekremkocak.weatherapplication.adapter.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ekremkocak.weatherapplication.adapter.listeners.CountryItemClickListener
import com.ekremkocak.weatherapplication.data.network.response.search.Result
import com.ekremkocak.weatherapplication.data.network.response.weather.Weather
import com.ekremkocak.weatherapplication.databinding.ItemDashboardWeatherBinding
import com.ekremkocak.weatherapplication.databinding.ItemSearchCountryRecylerviewBinding
import com.ekremkocak.weatherapplication.utils.Constants
import com.ekremkocak.weatherapplication.utils.Prefs

class DashboardWeatherAdapter(val result: ArrayList<Weather>): RecyclerView.Adapter<ViewHolder>() {




    inner class DailyWeatherViewHolder(val binding: ItemDashboardWeatherBinding) :
        ViewHolder(binding.root) {

        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemDashboardWeatherBinding.inflate(inflater,parent,false);
        return DailyWeatherViewHolder(view)
    }

    override fun getItemCount(): Int {

        return result.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        (holder as DailyWeatherViewHolder).binding.apply {
            result = this@DashboardWeatherAdapter.result[position]
        }
    }





}