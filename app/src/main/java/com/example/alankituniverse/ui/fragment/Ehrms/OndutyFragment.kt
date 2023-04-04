package com.example.alankituniverse.ui.fragment.Ehrms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.alankituniverse.R
import com.example.alankituniverse.databinding.FragmentOndutyBinding
import com.example.alankituniverse.ui.fragment.BaseFragment


class OndutyFragment : BaseFragment<FragmentOndutyBinding>(R.layout.fragment_onduty) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onduty, container, false)
    }

}