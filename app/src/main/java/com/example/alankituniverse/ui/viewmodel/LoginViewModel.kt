package com.example.alankituniverse.ui.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.alankituniverse.data.response.LoginResponse
import com.example.alankituniverse.data.respository.MainRepository
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
class LoginViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel() {
    var empId = AppConstants.EMPTY
    var password = AppConstants.EMPTY

    var errorMsgObserver = MutableLiveData(AppConstants.EMPTY)

    private val _loginObserver = SingleLiveData<Resource<LoginResponse>>()
    val loginResponse: SingleLiveData<Resource<LoginResponse>>
        get() = _loginObserver

    init {

    }

    fun onSubmitClick(view: View) {
        errorMsgObserver.value = AppConstants.EMPTY
        // if (!validateLoginInput()) return@onSubmitClick
        _loginObserver.value = Resource.Loading<Nothing>()
// TODO: change the parameter here  
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiRequest {
                        mainRepository.appLogin(
                            hashMapOf(
                                "employee code" to empId,
                                "password" to password
                            ).toGsonJsonObject()
                        )
                    }
                }
                _loginObserver.value = Resource.Success(response)

            } catch (e: Exception) {
                _loginObserver.value = Resource.Failure(e)
            }
        }
    }

    fun validateLoginInput(): Boolean {
        val message = if (!empId.isEmpty()) {
            if (!password.isEmpty()) {
                return true
            } else "Please Enter Your Password"
        } else "Please Enter Employee ID"
        errorMsgObserver.value = message
        return false
    }
}