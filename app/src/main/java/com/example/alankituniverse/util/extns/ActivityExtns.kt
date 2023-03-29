package com.example.alankituniverse.util.extns

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.WindowManager

fun Activity.fullScreen() {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
    fun Activity.startIntent(context: Context, activity: Activity) {
        val intent: Intent = Intent(context, activity.javaClass)
        startActivity(intent)
    }
}