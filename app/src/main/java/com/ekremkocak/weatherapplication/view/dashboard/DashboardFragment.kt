package com.ekremkocak.weatherapplication.view.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.ekremkocak.weatherapplication.R
import com.ekremkocak.weatherapplication.base.BaseFragment
import com.ekremkocak.weatherapplication.databinding.FragmentDashboardBinding
import com.ekremkocak.weatherapplication.viewmodel.dashboard.DashboardViewModel
import com.ekremkocak.weatherapplication.adapter.view_pager.ViewPagerAdapter
import com.ekremkocak.weatherapplication.databinding.DashboardSliderBinding
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DashboardFragment: BaseFragment() {

    private val viewModel: DashboardViewModel by viewModels()
    private var _binding: FragmentDashboardBinding? = null
    private lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var dotsIndicator: DotsIndicator
    private val binding get() = _binding
    private lateinit var uiStateScope: Job

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDashboardBinding.bind(view)

        initView()
        initCollect()
        initListeners()

    }

    fun initCollect(){
        uiStateScope = lifecycleScope.launch {
            viewModel.weatherUIState.collect {
                when (it) {
                    is DashboardViewModel.ViewState.ShowLoading -> {
                        // mainActivity.progressBar.isVisible = it.isShow
                    }
                    is DashboardViewModel.ViewState.ShowErrorMessage -> {
                        Toast.makeText(requireContext(), getString(R.string.app_name), Toast.LENGTH_SHORT).show()
                    }
                    is DashboardViewModel.ViewState.OnLoaded -> {
                        //binding değiştiği için viewmodelde yaptım
                    }
                    else -> Unit
                }
            }
        }
    }
    private fun initListeners() {

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {//now non required
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                Log.d("onPageSelected", position.toString())
            }

            override fun onPageScrollStateChanged(state: Int) {
                Log.d("onPageScrollState", state.toString())
            }
        })


    }
    fun getCountryWeather(position: Int,binding: DashboardSliderBinding){
        viewModel.loadContryWeather(viewPagerAdapter.countryArray[position],binding,this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    private fun initView() {
        viewPager = requireView().findViewById(R.id.view_pager_on_boarding)
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter
        dotsIndicator = requireView().findViewById(R.id.dots_indicator)
        dotsIndicator.setViewPager(viewPager)
    }
}