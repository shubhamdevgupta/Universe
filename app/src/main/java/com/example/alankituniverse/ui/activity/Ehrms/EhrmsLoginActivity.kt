package com.example.alankituniverse.ui.activity.Ehrms

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.alankituniverse.R
import com.example.alankituniverse.databinding.ActivityEhrmsloginBinding
import com.example.alankituniverse.ui.fragment.Ehrms.EhrmsFragment
import com.example.alankituniverse.ui.viewmodel.LoginViewModel
import com.example.alankituniverse.util.helper.AppUtil
import com.example.alankituniverse.util.helper.makeToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EhrmsLoginActivity : AppCompatActivity() {

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

        binding.ivMain.setOnClickListener {
        }
        binding.btnLogin.setOnClickListener {
            makeToast("clicked")
            setFragment(EhrmsFragment.newInstance())
        }

    }

    fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, fragment).commit()
    }
}