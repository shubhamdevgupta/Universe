package com.example.alankituniverse.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.alankituniverse.databinding.ActivityEhrmsloginBinding
import com.example.alankituniverse.ui.viewmodel.ehrms.LoginViewModel
import com.example.alankituniverse.util.helper.AppUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EhrmsMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEhrmsloginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEhrmsloginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val deviceId = AppUtil.getDeviceID(this)
        Log.d("MYTAG", "onCreate: " + deviceId)
    }
}