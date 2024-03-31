package com.ekremkocak.weatherapplication.adapter.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ekremkocak.weatherapplication.adapter.listeners.CountryItemClickListener
import com.ekremkocak.weatherapplication.data.network.response.search.Result
import com.ekremkocak.weatherapplication.data.network.response.search.SearchCountryResponse
import com.ekremkocak.weatherapplication.databinding.ItemSearchCountryRecylerviewBinding
import com.ekremkocak.weatherapplication.utils.Constants
import com.ekremkocak.weatherapplication.utils.Prefs

class SearchCountryAdapter(val context: Context,val countryList: List<Result>,var mCountryList : MutableSet<String>): RecyclerView.Adapter<ViewHolder>(),CountryItemClickListener {






    inner class CountryViewHolder(val binding: ItemSearchCountryRecylerviewBinding) :
        ViewHolder(binding.root) {

        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        println("adapter created")
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemSearchCountryRecylerviewBinding.inflate(inflater,parent,false);
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        println("country count: ${countryList.size}")
        return countryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        (holder as CountryViewHolder).binding.apply {
            clickListener = this@SearchCountryAdapter
            country = countryList[position]
        }
    }

    override fun onClick(result: Result) {
        var lastValue = Prefs.getKeySharedPreferencesBoolean(context,Constants.COUNTRY+""+result.country)
        Prefs.setKeySharedPreferencesBoolean(context,Constants.COUNTRY+""+result.country,!lastValue)
        result.enable = !lastValue
        notifyDataSetChanged()
        if (!lastValue){
            addCountryPrefListAndSave(result.country[0].value)
        }else{
            removeCountryPrefListAndSave(result.country[0].value)
        }
        println("click last value = ${lastValue}"+lastValue)
    }

    fun addCountryPrefListAndSave(countryKey: String){
        if (!mCountryList.contains(countryKey)){
            mCountryList.add(countryKey)
        }
        Prefs.setStringListSharedPreferences(context,mCountryList,Constants.COUNTRY)
    }
    fun removeCountryPrefListAndSave(countryKey: String){
        if (!mCountryList.contains(countryKey)){
            mCountryList.remove(countryKey)
        }
        Prefs.setStringListSharedPreferences(context,mCountryList,Constants.COUNTRY)
    }

}