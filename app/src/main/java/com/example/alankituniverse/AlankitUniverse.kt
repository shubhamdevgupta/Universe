package com.example.alankituniverse

import android.app.Application
import android.content.pm.PackageManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AlankitUniverse : Application() {
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