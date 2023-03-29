package com.example.alankituniverse.util.helper

import android.content.Context
import android.widget.Toast

fun Context.makeToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_LONG).show()
}