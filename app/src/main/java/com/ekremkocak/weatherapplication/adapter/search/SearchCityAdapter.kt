package com.ekremkocak.weatherapplication.adapter.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ekremkocak.weatherapplication.adapter.listeners.CountryItemClickListener
import com.ekremkocak.weatherapplication.data.network.response.search.Result
import com.ekremkocak.weatherapplication.databinding.ItemSearchCountryRecylerviewBinding
import com.ekremkocak.weatherapplication.utils.Constants
import com.ekremkocak.weatherapplication.utils.Prefs

class SearchCityAdapter(val context: Context, private val cityList: List<Result>, private var mCityList : MutableSet<String>): RecyclerView.Adapter<ViewHolder>(),CountryItemClickListener {

    inner class CountryViewHolder(val binding: ItemSearchCountryRecylerviewBinding) :
        ViewHolder(binding.root) {

        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemSearchCountryRecylerviewBinding.inflate(inflater,parent,false);
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        (holder as CountryViewHolder).binding.apply {
            clickListener = this@SearchCityAdapter
            result = cityList[position]
        }
    }

    override fun onClick(result: Result) {
        updateList(result)
        notifyItemChanged(cityList.indexOf(result))
    }
    private fun updateList(result: Result){
        val lastValue = Prefs.getKeySharedPreferencesBoolean(context,Constants.CITY+""+result.areaName)
        Prefs.setKeySharedPreferencesBoolean(context,Constants.CITY+""+result.areaName,!lastValue)
        result.enable = !lastValue
        if (!lastValue){
            addCountryPrefListAndSave(result.areaName[0].value)
        }else{
            removeCountryPrefListAndSave(result.areaName[0].value)
        }
    }

    private fun addCountryPrefListAndSave(countryKey: String){
        if (!mCityList.contains(countryKey)){
            mCityList.add(countryKey)
        }
        Prefs.setStringListSharedPreferences(context,mCityList,Constants.CITY)
    }
    private fun removeCountryPrefListAndSave(countryKey: String){
        if (mCityList.contains(countryKey)){
            mCityList.remove(countryKey)
        }
        Prefs.setStringListSharedPreferences(context,mCityList,Constants.CITY)
    }

}