package com.example.alankituniverse.ui.fragment.Ehrms

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.alankituniverse.R
import com.example.alankituniverse.data.response.LoginResponse
import com.example.alankituniverse.databinding.FragmentLoginEhrmsBinding
import com.example.alankituniverse.ui.dialog.AppDialog
import com.example.alankituniverse.ui.fragment.BaseFragment
import com.example.alankituniverse.ui.viewmodel.ehrms.LoginViewModel
import com.example.alankituniverse.util.api.Resource
import com.example.alankituniverse.util.extns.handelNetworkFailure
import com.example.alankituniverse.util.extns.hide
import com.example.alankituniverse.util.extns.setChildAlpha
import com.example.alankituniverse.util.extns.show
import com.example.alankituniverse.util.helper.makeToast


class LoginEhrmsFragment : BaseFragment<FragmentLoginEhrmsBinding>(R.layout.fragment_login_ehrms) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressLayout.tvLoading.text="Please wait login..."
        subscriberObservers()
    }


    private fun subscriberObservers() {
        viewModel.loginResponse.singleObserver(this, Observer {
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
                    setVisibilityWithAlpha(true)
                    activity?.handelNetworkFailure(it.exception)
                }
            }
        })
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

    private fun onLoginSuccess(response: LoginResponse) {
        Log.d("MYTAG", "onLoginResponse: "+response)
        if (response.Status == 1) {
            activity?.makeToast("succesfully")
        } else if (response.Status == 0) {
            activity?.makeToast("failed")
        } else AppDialog.failed(requireActivity(), response.Message)
    }

}