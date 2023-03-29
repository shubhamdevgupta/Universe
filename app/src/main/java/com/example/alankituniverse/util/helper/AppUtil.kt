package com.example.alankituniverse.util.helper

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import com.example.alankituniverse.BuildConfig

object AppUtil {

    fun logger(msg: String) {
        if (BuildConfig.DEBUG) Log.d("Applogger", "logger: " + msg)
    }

    fun getDeviceID(context: Context?): String {
        var imei: String? = null
        try {
            val telephonyManager =
                context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            imei = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                try {
                    telephonyManager.imei
                } catch (e: Exception) {
                    e.printStackTrace()
                    Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
                }
            } else telephonyManager.deviceId
        } catch (e: Exception) {
            imei = null
        }
        return imei ?: ""
    }

}