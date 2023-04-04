package com.example.alankituniverse

import android.app.Application
import android.content.pm.PackageManager
import com.example.alankituniverse.util.helper.AppUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AlankitUniverse : Application() {
    companion object {
        lateinit var deviceId: String
        var currentAppVersion: String? = null
    }

    override fun onCreate() {
        super.onCreate()
        deviceId = AppUtil.getDeviceID(this)
        currentAppVersion = getCurrentAppVersion(this)

    }

    private fun getCurrentAppVersion(alankitUniverse: AlankitUniverse): String? {
        var currentVersion: String? = "0"
        try {
            currentVersion = alankitUniverse.packageManager.getPackageInfo(
                alankitUniverse.packageName, 0
            ).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return currentVersion
    }
}