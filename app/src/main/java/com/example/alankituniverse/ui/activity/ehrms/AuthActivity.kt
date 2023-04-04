package com.example.alankituniverse.ui.activity.ehrms

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.alankituniverse.AlankitUniverse
import com.example.alankituniverse.R
import com.example.alankituniverse.util.helper.AppUpdateUtil
import com.example.alankituniverse.util.helper.AppUtil
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }

    private fun checkUpdateplayStore() {
        val appUpdateManager = AppUpdateManagerFactory.create(this)
        // Returns an intent object that you use to check for an update.
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        // Checks that the platform will allow the specified type of update.
        Log.d("test", "Checking for updates")
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                // Request the update.
                Log.d("test", "Update available")
            } else {
                Log.d("test", "No Update available")
            }
        }
    }

    private fun checkUpdate() {
        val mPackageName = packageName
        val playStoreUrl = "https://play.google.com/store/apps/details?id=%s"

        // TODO: loook when you do update in application 
        lifecycleScope.launch(Dispatchers.Main) {
            val version: String = withContext(Dispatchers.IO) {
                AppUpdateUtil.getPlayStoreAppVersion(String.format(playStoreUrl, mPackageName))
            } ?: return@launch
            try {
                val playVersion = version.toFloat()
                val currentVersion = AlankitUniverse.currentAppVersion?.toFloat()
                currentVersion?.let {
                    if (playVersion > currentVersion) {
                        // AppUpdateUtil.updateDialog(this@AuthActivity, currentVersion, playVersion)
                    } else AppUtil.logger("UpdateCheck : Play version is not greater than App version")
                } ?: AppUtil.logger("UpdateCheck : current version is null")
            } catch (e: Exception) {
                AppUtil.logger("UpdateCheck : ${e.message}")
            }
        }
    }
}