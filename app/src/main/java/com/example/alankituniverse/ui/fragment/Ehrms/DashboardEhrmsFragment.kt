package com.example.alankituniverse.ui.fragment.Ehrms

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.alankituniverse.R
import com.example.alankituniverse.databinding.FragmentDashboardEhrmsBinding
import com.example.alankituniverse.ui.fragment.BaseFragment
import com.example.alankituniverse.ui.viewmodel.ehrms.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardEhrmsFragment :
    BaseFragment<FragmentDashboardEhrmsBinding>(R.layout.fragment_dashboard_ehrms) {

    private val viewModel: DashboardViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        subscribeObserver()

    }

    private fun subscribeObserver() {

    }

    companion object {
        fun newInstance(): DashboardEhrmsFragment {
            return DashboardEhrmsFragment()
        }
    }
}