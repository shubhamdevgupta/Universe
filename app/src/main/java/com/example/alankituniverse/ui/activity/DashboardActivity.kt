package com.example.alankituniverse.ui.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.alankituniverse.R
import com.example.alankituniverse.databinding.ActivityDashboardBinding
import com.example.alankituniverse.ui.activity.Ehrms.EhrmsLoginActivity
import com.example.alankituniverse.util.helper.makeToast

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cvGst.setOnClickListener {
            openGST(it.context, "alankitgspkhk.alankitgsp")
        }
        binding.cvTrade.setOnClickListener {
            openEasyTrade(it.context, "com.rs.easytrade")
        }
        binding.cvNps.setOnClickListener {
            this.makeToast("NPS Clicked")
        }
        binding.cvBranch.setOnClickListener {
            this.makeToast("Branch Clicked")
        }
        binding.cvEmployee.setOnClickListener {

            eHrmsLogin()
        }
        binding.cvRecord.setOnClickListener {
            this.makeToast("Record Expert Clicked")
        }
        binding.cvRta.setOnClickListener {
            this.makeToast("RTA Clicked")
        }
    }

    private fun eHrmsLogin() {
        val intent = Intent(this, EhrmsLoginActivity::class.java)
        startActivity(intent)
    }

    private fun openEasyTrade(context: Context, pkg: String) {
        val intent: Intent? = context.packageManager.getLaunchIntentForPackage(pkg)
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        } else {
            val intent2 = Intent(Intent.ACTION_VIEW)
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val uri: String = "market://details?id=" + "com.rs.easytrade"
            intent2.data = Uri.parse(uri)
            startActivity(intent2)
        }
    }

    private fun openGST(context: Context, pkg: String) {
        val intent: Intent? = context.packageManager.getLaunchIntentForPackage(pkg)
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        } else {
            val intent2 = Intent(Intent.ACTION_VIEW)
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val uri: String = "market://details?id=" + "alankitgspkhk.alankitgsp&hl=en"
            intent2.data = Uri.parse(uri)
            startActivity(intent2)
        }
    }
}