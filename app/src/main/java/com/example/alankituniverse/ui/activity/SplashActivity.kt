package com.example.alankituniverse.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alankituniverse.R
import com.example.alankituniverse.util.extns.fullScreen

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        delayandlaunchLogin()
        fullScreen()

    }

    private fun delayandlaunchLogin() {
        val timer: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(2500)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    val dashboardIntent: Intent =
                        Intent(this@SplashActivity, DashboardActivity::class.java)
                    startActivity(dashboardIntent)
                    finish()
                }
            }
        }
        timer.start()
    }
}