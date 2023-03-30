package com.example.alankituniverse.ui.viewmodel.ehrms

import com.example.alankituniverse.data.local.AppPreference
import com.example.alankituniverse.data.respository.MainRepository
import com.example.alankituniverse.ui.viewmodel.BaseViewModel
import com.example.alankituniverse.util.helper.ApiData
import com.example.alankituniverse.util.helper.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val respositry: MainRepository,
    private val apiData: ApiData,
    private val appPreference: AppPreference
) : BaseViewModel() {
    var earnedLeave: String = AppConstants.EMPTY
    var casualLeave: String = AppConstants.EMPTY
    var medicalLeave: String = AppConstants.EMPTY



}