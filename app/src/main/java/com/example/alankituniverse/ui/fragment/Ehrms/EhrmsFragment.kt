package com.example.alankituniverse.ui.fragment.Ehrms

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.alankituniverse.R
import com.example.alankituniverse.databinding.FragmentEhrmsBinding
import com.example.alankituniverse.ui.fragment.BaseFragment
import com.example.alankituniverse.ui.viewmodel.ehrms.EhrmsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EhrmsFragment : BaseFragment<FragmentEhrmsBinding>(R.layout.fragment_ehrms) {

    private val viewModel: EhrmsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        subscribeObserver()

    }

    private fun subscribeObserver() {

    }

    companion object {
        fun newInstance(): EhrmsFragment {
            return EhrmsFragment()
        }
    }
}