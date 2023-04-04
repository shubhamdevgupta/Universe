package com.example.alankituniverse.ui.fragment.Ehrms

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.alankituniverse.R
import com.example.alankituniverse.data.response.LoginResponse
import com.example.alankituniverse.databinding.FragmentLoginEhrmsBinding
import com.example.alankituniverse.ui.dialog.AppDialog
import com.example.alankituniverse.ui.fragment.BaseFragment
import com.example.alankituniverse.ui.viewmodel.ehrms.LoginViewModel
import com.example.alankituniverse.util.api.Resource
import com.example.alankituniverse.util.extns.handleNetworkFailure
import com.example.alankituniverse.util.extns.hide
import com.example.alankituniverse.util.extns.setChildAlpha
import com.example.alankituniverse.util.extns.show
import com.example.alankituniverse.util.helper.makeToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginEhrmsFragment : BaseFragment<FragmentLoginEhrmsBinding>(R.layout.fragment_login_ehrms) {
    private val viewModel: LoginViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.progressLayout.tvLoading.text = "Please wait login..."
        subscriberObservers()
    }

    private fun subscriberObservers() {
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.progressLayout.tvLoading.text = "Please wait login..."
                    setVisibilityWithAlpha(false)
                }
                is Resource.Success -> {
                    setVisibilityWithAlpha(true)
                    onLoginSuccess(it.data)
                }
                is Resource.Failure -> {
                    activity?.makeToast(it.exception.message.toString())
                    setVisibilityWithAlpha(true)
                    activity?.handleNetworkFailure(it.exception)
                }
            }
        })

    }

    private fun onLoginSuccess(response: LoginResponse) {
        if (response.Status == 1) {
            val directions =
                LoginEhrmsFragmentDirections.actionLoginEhrmsFragmentToDashboardEhrmsFragment(
                    response.Data.userName
                )
            findNavController().navigate(directions)
        } else if (response.Status == 0) {
            AppDialog.failed(requireActivity(), response.Message)
        } else AppDialog.failed(requireActivity(), response.Message)
    }

    private fun setVisibilityWithAlpha(visibility: Boolean = false) {

        binding.let {
            if (visibility) {
                it.btnLogin.show()
                it.progressLayout.rootLayout.hide()
                it.svRoot.setChildAlpha(visibility = true)
            } else {
                it.btnLogin.hide()
                it.progressLayout.rootLayout.show()
                it.svRoot.setChildAlpha(
                    visibility = false,
                    exceptView = it.progressLayout.rootLayout
                )
            }
        }
    }

}