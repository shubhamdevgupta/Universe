package com.example.alankituniverse.ui.viewmodel.ehrms

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alankituniverse.data.local.AppPreference
import com.example.alankituniverse.data.model.User
import com.example.alankituniverse.data.response.LoginResponse
import com.example.alankituniverse.data.respository.MainRepository
import com.example.alankituniverse.ui.viewmodel.BaseViewModel
import com.example.alankituniverse.util.api.Resource
import com.example.alankituniverse.util.extns.toGsonJsonObject
import com.example.alankituniverse.util.helper.AppConstants
import com.example.alankituniverse.util.helper.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val appPreference: AppPreference
) :
    BaseViewModel() {
    var empId = AppConstants.EMPTY
    var password = AppConstants.EMPTY

    var errorMsgObserver = MutableLiveData(AppConstants.EMPTY)

    init {
        empId = appPreference.employeeID
        password = appPreference.password
    }

    private val _loginObserver = SingleLiveData<Resource<LoginResponse>>()
    val loginResponse: SingleLiveData<Resource<LoginResponse>>
        get() = _loginObserver

    fun onSubmitClick(view: View) {
        errorMsgObserver.value = AppConstants.EMPTY
        if (!validateLoginInput()) return@onSubmitClick
        _loginObserver.value = Resource.Loading<Nothing>()
// TODO: change the parameter here  
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiRequest {
                        mainRepository.appLogin(
                            hashMapOf(
                                "Ssn" to empId,
                                "Pass" to password
                            ).toGsonJsonObject()
                        )
                    }
                }
                setLoginCheck(response)
                Log.d("MYTAG", "onSubmitClick: " + response)
                _loginObserver.value = Resource.Success(response)
            } catch (e: Exception) {
                _loginObserver.value = Resource.Failure(e)
            }
        }
    }

    fun validateLoginInput(): Boolean {
        val message = if (empId.isNotEmpty()) {
            if (password.isNotEmpty()) {
                return true
            } else "Please Enter Your Password"
        } else "Please Enter Employee ID"
        errorMsgObserver.value = message
        Log.d("MYTAG", "validateLoginInput: " + errorMsgObserver.value)
        return false
    }

    private fun setLoginCheck(response: LoginResponse) {
        appPreference.employeeID = AppConstants.EMPTY
        appPreference.password = AppConstants.EMPTY
        appPreference.user = User()

        if (response.Status == 1) {
            appPreference.employeeID = empId
            appPreference.password = password
            appPreference.user = response.Data
        }
    }

}