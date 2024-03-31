package com.ekremkocak.weatherapplication.view.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import com.ekremkocak.weatherapplication.R
import com.ekremkocak.weatherapplication.base.BaseFragment
import com.ekremkocak.weatherapplication.databinding.FragmentDashboardBinding
import com.ekremkocak.weatherapplication.viewmodel.dashboard.DashboardViewModel
import com.ekremkocak.weatherapplication.adapter.view_pager.ViewPagerAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class DashboardFragment: BaseFragment() {

    private val viewModel: DashboardViewModel by viewModels()
    private var _binding: FragmentDashboardBinding? = null
    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var dotsIndicator: DotsIndicator
    private val binding get() = _binding

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

        /*viewModel.getAllViewModel(
            Prefs.getKeySharedPreferences(
                requireContext(),
                Constants.PREF_TOKEN
            )
        )*/
        setUpRecyclerView()
        initListeners()
       // viewModel.getContryWeather("Turkey");


        Navigation.findNavController(requireView()).navigate(R.id.action_dashboardFragment_to_searchFragment)

    }

    private fun initListeners() {

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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
    fun getCountryWeather(position: Int){
        viewModel.getContryWeather(viewPagerAdapter.countryArray[position],viewPagerAdapter.countryBindingList[position])
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView(){
        /*
        viewModel.mSearchList.observe(viewLifecycleOwner) {
            val adapter = SearchViewParentRVAdapter(requireContext(), it)
            binding!!.rvSearch.layoutManager = LinearLayoutManager(requireContext())
            binding!!.rvSearch.adapter = adapter
        }*/
    }

    private fun initView() {
        viewPager = requireView().findViewById(R.id.view_pager_on_boarding)
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter
        dotsIndicator = requireView().findViewById(R.id.dots_indicator)
        dotsIndicator.setViewPager(viewPager)



    }
}