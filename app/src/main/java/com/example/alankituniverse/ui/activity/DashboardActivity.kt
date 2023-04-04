package com.example.alankituniverse.ui.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.alankituniverse.R
import com.example.alankituniverse.databinding.ActivityDashboardBinding
import com.example.alankituniverse.ui.activity.ehrms.EhrmsMainActivity
import com.example.alankituniverse.util.extns.getCurrentDate
import com.example.alankituniverse.util.helper.makeToast
import com.google.android.material.navigation.NavigationView

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navigationView: NavigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    makeToast("nav home Clickedd ")
                    //write your implementation here
                    if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        binding.drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    true
                }
                else -> {
                    if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        binding.drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    false
                }
            }
        }








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
            Log.d("MYTAG", "onCreate: " + this.getCurrentDate())
        }
    }

    private fun eHrmsLogin() {
        val intent = Intent(this, EhrmsMainActivity::class.java)
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