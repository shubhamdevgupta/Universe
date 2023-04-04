package com.example.alankituniverse.ui.viewmodel.ehrms

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alankituniverse.data.local.AppPreference
import com.example.alankituniverse.data.response.GatePassResponse
import com.example.alankituniverse.data.response.LeaveDataResponse
import com.example.alankituniverse.data.respository.MainRepository
import com.example.alankituniverse.data.service.GatePassData
import com.example.alankituniverse.data.service.LeaveData
import com.example.alankituniverse.ui.viewmodel.BaseViewModel
import com.example.alankituniverse.util.api.Resource
import com.example.alankituniverse.util.helper.ApiData
import com.example.alankituniverse.util.helper.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val apiData: ApiData,
    private val appPreference: AppPreference
) : BaseViewModel() {
    var earnedLeave: String = AppConstants.EMPTY
    var casualLeave: String = AppConstants.EMPTY
    var medicalLeave: String = AppConstants.EMPTY

    var dateTime: String = AppConstants.EMPTY

    var errorMsgObserver = MutableLiveData(AppConstants.EMPTY)

    private val _gateObserver = MutableLiveData<Resource<GatePassResponse>>()
    val gatePassResponse: MutableLiveData<Resource<GatePassResponse>>
        get() = _gateObserver

    private val _leaveObserver = MutableLiveData<Resource<LeaveDataResponse>>()
    val leaveResponse: MutableLiveData<Resource<LeaveDataResponse>>
        get() = _leaveObserver

    fun onGatePass() {
        errorMsgObserver.value = AppConstants.EMPTY
        _gateObserver.value = Resource.Loading<Nothing>()
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiRequest {
                        mainRepository.getGatePass(
                            GatePassData(appPreference.employeeID, dateTime)
                        )
                    }
                }
                _gateObserver.value = Resource.Success(response)
            } catch (e: Exception) {
                _gateObserver.value = Resource.Failure(e)
            }
        }
    }

    fun onLeaveCheck() {
        errorMsgObserver.value = AppConstants.EMPTY
        _leaveObserver.value = Resource.Loading<Nothing>()
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiRequest {
                        mainRepository.getLeaveData(
                            LeaveData(appPreference.employeeID, appPreference.user.userName)
                        )
                    }
                }
                _leaveObserver.value = Resource.Success(response)
            } catch (e: Exception) {
                _leaveObserver.value = Resource.Failure(e)
            }
        }
    }


}


