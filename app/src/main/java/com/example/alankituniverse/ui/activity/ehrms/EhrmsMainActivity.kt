package com.example.alankituniverse.ui.activity.ehrms

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.alankituniverse.R
import com.example.alankituniverse.databinding.ActivityEhrmsloginBinding
import com.example.alankituniverse.ui.fragment.Ehrms.DashboardEhrmsFragment
import com.example.alankituniverse.ui.fragment.Ehrms.LeaveFragment
import com.example.alankituniverse.util.helper.makeToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EhrmsMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEhrmsloginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_ehrmslogin
        )


        val toolbar = findViewById<View>(com.example.alankituniverse.R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setIcon(R.drawable.baseline_view_headline_24)

        setFragment(DashboardEhrmsFragment.newInstance())

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_leave -> {
                    setFragment(LeaveFragment.newInstance())
                    makeToast("leave clicked")
                    true
                }
                R.id.nav_application_status -> {
                    makeToast("application status ")
                    true
                }
                R.id.nav_onduty -> {
                    true
                }
                R.id.nav_field_duty -> {
                    true
                }
                R.id.nav_apply_conv -> {
                    true
                }
                R.id.nav_extra_work -> {
                    true
                }
                R.id.nav_employee_gate -> {
                    true
                }
                R.id.nav_hod_approv -> {
                    true
                }
                else ->
                    false

            }

        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, fragment).commit()
    }
    private fun initView() {

    }


    override fun onBackPressed() {
        super.onBackPressed()
    }
}