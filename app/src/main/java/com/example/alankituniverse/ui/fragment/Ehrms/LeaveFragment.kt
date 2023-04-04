package com.example.alankituniverse.ui.fragment.Ehrms

import android.os.Bundle
import android.view.View
import com.example.alankituniverse.R
import com.example.alankituniverse.databinding.FragmentLeaveBinding
import com.example.alankituniverse.ui.fragment.BaseFragment

class LeaveFragment : BaseFragment<FragmentLeaveBinding>(R.layout.fragment_leave) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
    }


    companion object {
        fun newInstance(): LeaveFragment {
            return LeaveFragment()
        }
    }
}