package com.example.alankituniverse.util.helper

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.TextView
import com.example.alankituniverse.BuildConfig
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

object AppUpdateUtil {

    fun getPlayStoreAppVersion(appUrlString: String): String? {
        val currentVersionPatternSeq =
            "<div[^>]*?>Current\\sVersion</div><span[^>]*?>(.*?)><div[^>]*?>(.*?)><span[^>]*?>(.*?)</span>"
        val appVersionPatternSeq = "htlgb\">([^<]*)</s"
        try {
            val connection = URL(appUrlString).openConnection()
            connection.setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6"
            )
            BufferedReader(InputStreamReader(connection.getInputStream()))
                .use { br ->
                    val sourceCode = StringBuilder()
                    var line: String?
                    while (br.readLine().also { line = it } != null) sourceCode.append(line)
                    // Get the current version pattern sequence
                    val versionString = getAppVersion(
                        currentVersionPatternSeq,
                        sourceCode.toString()
                    )
                        ?: return null
                    // get version from "htlgb">X.X.X</span>
                    return getAppVersion(
                        appVersionPatternSeq,
                        versionString
                    )
                }
        } catch (e: IOException) {
            AppUtil.logger("UpdateCheck : ${e.message}")
            e.printStackTrace()
        }
        return null
    }

    private fun getAppVersion(patternString: String, input: String): String? {
        try {
            val pattern =
                Pattern.compile(patternString)
            val matcher = pattern.matcher(input)
            if (matcher.find()) return matcher.group(1)
        } catch (e: PatternSyntaxException) {
            e.printStackTrace()
        }
        return null
    }
// TODO: app update function need to uncommment this 
/*
    fun updateDialog(activity: Activity, currentVersion: Float?, playVersion: Float) {
        val dialog = AppDialog.updateDialog(activity)
        val btnUpdate = dialog.findViewById<Button>(R.id.btn_update)
        val tvCurrentVersion = dialog.findViewById<TextView>(R.id.tv_currentVersion)
        val tvUpdateVersion = dialog.findViewById<TextView>(R.id.tv_updateVersion)
        tvCurrentVersion.text = "Current Version : $currentVersion"
        tvUpdateVersion.text = "Update Version  : $playVersion"
        btnUpdate.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID)
            )
            activity.startActivity(intent)
        }
    }
*/
}