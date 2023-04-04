package com.example.alankituniverse.ui.fragment.Ehrms

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alankituniverse.R
import com.example.alankituniverse.data.adapter.GatePassAdapter
import com.example.alankituniverse.data.response.GatePassResponse
import com.example.alankituniverse.data.response.LeaveDataResponse
import com.example.alankituniverse.databinding.FragmentDashboardEhrmsBinding
import com.example.alankituniverse.ui.dialog.AppDialog
import com.example.alankituniverse.ui.fragment.BaseFragment
import com.example.alankituniverse.ui.viewmodel.ehrms.DashboardViewModel
import com.example.alankituniverse.util.api.Resource
import com.example.alankituniverse.util.extns.*
import com.example.alankituniverse.util.helper.makeToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardEhrmsFragment :
    BaseFragment<FragmentDashboardEhrmsBinding>(R.layout.fragment_dashboard_ehrms) {

    private val viewModel: DashboardViewModel by viewModels()
//    private val args: DashboardEhrmsFragmentArgs by navArgs()
    private lateinit var drawer: DrawerLayout
    lateinit var actionBarDrawerToggel: ActionBarDrawerToggle

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.navFragment.viewModel = viewModel
    //    binding.navFragment.tvUsername.text = args.username

        actionBarDrawerToggel =
            ActionBarDrawerToggle(activity, binding.drawer, R.string.nav_open, R.string.nav_close)
        binding.drawer.addDrawerListener(actionBarDrawerToggel)
        actionBarDrawerToggel.syncState()

        binding.navFragment.llearnedLeave.setOnClickListener {
            context?.makeToast("clicked")
        }

        viewModel.dateTime = activity?.getCurrentDate()!!
        //viewModel.dateTime = "2023-04-01"
        //viewModel.onLeaveCheck()
        //viewModel.onGatePass()
        subscribeObserver()

    }

    private fun subscribeObserver() {
        viewModel.leaveResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.navFragment.progressLayout.tvLoading.text = "Please wait..."
                    setVisibilityWithAlpha(false)
                }
                is Resource.Success -> {
                    setVisibilityWithAlpha(true)
                    onLeaveDataSuccess(it.data)
                    Log.d("MYTAG", "subscribeObserver: " + it.data)
                }
                is Resource.Failure -> {
                    setVisibilityWithAlpha(true)
                    activity?.makeToast(it.exception.message.toString())
                    activity?.handleNetworkFailure(it.exception)
                }
            }
        })

        viewModel.gatePassResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.navFragment.progressLayout.tvLoading.text = "Please wait..."
                    setVisibilityWithAlpha(false)
                }
                is Resource.Success -> {
                    setVisibilityWithAlpha(true)
                    onGateDataSuccess(it.data)
                }
                is Resource.Failure -> {
                    setVisibilityWithAlpha(true)
                    activity?.handleNetworkFailure(it.exception)
                }
            }
        })
    }

    private fun onGateDataSuccess(res: GatePassResponse) {
        if (res.status == 1) {
            if (res.data.isNotEmpty()) {
                setVisibilityForRecycleView(true)
                val adapter = GatePassAdapter()
                adapter.addItems(res.data)
                binding.navFragment.recycleGatepass.let { recyclerView ->
                    recyclerView.setHasFixedSize(false)
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = adapter
                }
            } else
                setVisibilityForRecycleView(false)
        } else if (res.status == 0) {
            setVisibilityForRecycleView(false)
        } else AppDialog.failed(requireActivity(), res.msg)

    }


    private fun onLeaveDataSuccess(res: LeaveDataResponse) {
        if (res.status == 1) {

            binding.navFragment.tvEarLeave.text = res.earnedLeave
            binding.navFragment.tvCasLeave.text = res.casualLeave
            binding.navFragment.tvMedLeave.text = res.medicalLeave

            /* viewModel.earnedLeave = res.earnedLeave
             viewModel.medicalLeave = res.medicalLeave
             viewModel.casualLeave = res.casualLeave*/
        } else if (res.status == 0) {
            AppDialog.failed(requireActivity(), res.message)
        } else AppDialog.failed(requireActivity(), res.message)
    }

    companion object {
        fun newInstance(): DashboardEhrmsFragment {
            return DashboardEhrmsFragment()
        }
    }

    private fun setVisibilityWithAlpha(visibility: Boolean = false) {

        binding.let {
            if (visibility) {
                it.navFragment.llMainLayout.show()
                it.navFragment.progressLayout.rootLayout.hide()
                it.navFragment.svRoot.setChildAlpha(visibility = true)
            } else {
                it.navFragment.llMainLayout.hide()
                it.navFragment.progressLayout.rootLayout.show()
                it.navFragment.svRoot.setChildAlpha(
                    visibility = false,
                    exceptView = it.navFragment.progressLayout.rootLayout
                )
            }
        }
    }

    private fun setVisibilityForRecycleView(visibility: Boolean = false) {
        if (visibility) {
            binding.navFragment.recycleGatepass.show()
            binding.navFragment.llNodata.hide()
        } else {
            binding.navFragment.recycleGatepass.hide()
            binding.navFragment.llNodata.show()
        }
    }

/*
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        if (item.itemId==R.id.nav_apply_conv)Log.d("MYTAG", "onNavigationItemSelected: leave clickedddddd")
            else Log.d("MYTAG", "onNavigationItemSelected: not clickeddd")

        when (item.itemId) {
            R.id.nav_leave -> {
                Log.d("MYTAG", "onNavigationItemSelected: leave clickedddddd")
            }
            R.id.nav_application_status -> {
                Log.d("MYTAG", "onNavigationItemSelected: " + R.id.nav_application_status)

            }
            R.id.nav_onduty -> {
                Log.d("MYTAG", "onNavigationItemSelected: " + R.id.nav_onduty)

            }
            R.id.nav_field_duty -> {
                Log.d("MYTAG", "onNavigationItemSelected: " + R.id.nav_field_duty)

            }
            R.id.nav_apply_conv -> {
                Log.d("MYTAG", "onNavigationItemSelected: " + R.id.nav_apply_conv)

            }
            R.id.nav_extra_work -> {
                Log.d("MYTAG", "onNavigationItemSelected: " + R.id.nav_extra_work)

            }
            R.id.nav_employee_gate -> {
                Log.d("MYTAG", "onNavigationItemSelected: " + R.id.nav_employee_gate)
            }
            R.id.nav_hod_approv -> {}
        }
        return true
    }
*/

}