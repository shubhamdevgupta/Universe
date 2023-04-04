package com.example.alankituniverse.ui.fragment.Ehrms.Gatepass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alankituniverse.R
import com.example.alankituniverse.databinding.FragmentGatepassBinding
import com.example.alankituniverse.ui.fragment.BaseFragment


class GatepassFragment : BaseFragment<FragmentGatepassBinding>(R.layout.fragment_gatepass) {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gatepass, container, false)
    }
}