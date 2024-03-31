package com.ekremkocak.weatherapplication.base


import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import dagger.hilt.android.AndroidEntryPoint
import java.io.*


@AndroidEntryPoint
open class BaseFragment : Fragment() {
    override fun onResume() {

        super.onResume()
    }



    fun Fragment.showDialogFragment(dialogFragment: DialogFragment, tag: String?) {
        activity?.showDialogFragment(dialogFragment, tag)
    }

    fun FragmentActivity.showDialogFragment(dialogFragment: DialogFragment, tag: String?) {
        val fragmentManager = supportFragmentManager ?: return
        dialogFragment.show(fragmentManager, tag)
    }


}