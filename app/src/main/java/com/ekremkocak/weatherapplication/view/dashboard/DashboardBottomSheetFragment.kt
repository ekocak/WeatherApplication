package com.ekremkocak.weatherapplication.view.dashboard

import android.os.Bundle
import android.view.*
import com.ekremkocak.weatherapplication.R
import com.ekremkocak.weatherapplication.databinding.BottomsheetDashboardBinding
import com.ekremkocak.weatherapplication.utils.RoundedBottonSheetDialogFragment


internal class DashboardBottomSheetFragment :
    RoundedBottonSheetDialogFragment(R.layout.bottomsheet_dashboard) {

    companion object {
        fun newInstance(
            eventUserTv: String,

        ) = DashboardBottomSheetFragment().apply {
            this.eventUserTv = eventUserTv

        }
    }

    private var eventUserTv: String? = null


    private var _binding: BottomsheetDashboardBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomsheetDashboardBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // binding?.eventUserTv?.text = eventUserTv

    }
}