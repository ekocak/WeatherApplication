package com.ekremkocak.weatherapplication.view.search



import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.core.widget.addTextChangedListener
import androidx.navigation.Navigation
import com.ekremkocak.weatherapplication.adapter.search.SearchCityAdapter
import com.ekremkocak.weatherapplication.base.BaseFragment
import com.ekremkocak.weatherapplication.databinding.FragmentSearchBinding
import com.ekremkocak.weatherapplication.utils.Constants
import com.ekremkocak.weatherapplication.utils.Prefs
import com.ekremkocak.weatherapplication.viewmodel.search.SearchPageViewModel



class SearchFragment : BaseFragment() {


    lateinit var mCountryList : MutableSet<String>

    private val viewModel: SearchPageViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)

        setUpRecyclerView()
        initListeners()
        initList()


    }

    private fun initList(){
        if (Prefs.getStringListSharedPreferences(requireContext(), Constants.CITY).isNullOrEmpty()){
            mCountryList = mutableSetOf()
            viewModel.mCityList = mCountryList
        }else{
            mCountryList = Prefs.getStringListSharedPreferences(requireContext(), Constants.CITY)!!
            viewModel.mCityList = mCountryList
        }

    }
    private fun initListeners() {
        binding?.imageViewBack?.setOnClickListener{
            Navigation.findNavController(requireView()).popBackStack()
            _binding?.editTextSearchText?.setText("")
        }
        binding?.editTextSearchText?.addTextChangedListener{
            viewModel.searchCountries(binding?.editTextSearchText?.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView(){

        viewModel.mList.observe(viewLifecycleOwner) {
            it?.let {
                val adapter = SearchCityAdapter(requireContext(),it,mCountryList)
                //StaggeredGridLayoutManager(mContext)
                binding!!.rvSearch.layoutManager = LinearLayoutManager(requireContext())
                binding!!.rvSearch.adapter = adapter
            }

        }
    }

}