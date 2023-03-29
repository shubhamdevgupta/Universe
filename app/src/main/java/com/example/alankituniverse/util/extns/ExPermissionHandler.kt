package com.example.alankituniverse.util.extns

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.alankituniverse.R
import com.example.alankituniverse.util.helper.makeToast
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener

private val locationReadPhoneStatePermissions by lazy {
    arrayListOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.GET_ACCOUNTS
    )
}

fun Activity.requestInitialPermissions(onAllPermissionGranted: () -> Unit) {

    val deniedList = ArrayList<String>()

    Dexter.withActivity(this)
        .withPermissions(locationReadPhoneStatePermissions)
        .withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                if (report.areAllPermissionsGranted()) onAllPermissionGranted()

                val deniedPermissionResponses = report.deniedPermissionResponses
                deniedPermissionResponses.forEach {
                    if (it.isPermanentlyDenied)

                        when (it.permissionName) {
                            "android.permission.READ_PHONE_STATE" -> deniedList.add("Read Phone State")
                            "android.permission.ACCESS_FINE_LOCATION" -> deniedList.add("Location")
                            "android.permission.GET_ACCOUNTS" -> deniedList.add("Accounts")
                        }
                }
                if (deniedList.size > 0) {
                    val message: String = if (deniedList.size == 1)
                        deniedList[0] + " permission need this app to use further feature"
                    else if (deniedList.size == 2)
                        deniedList[0] + " and " + deniedList[1] + " permissions need this app to use further feature"
                    else deniedList[0] + ", " + deniedList[1] + " and" + deniedList[2] + " permissions need this app to use further feature"
                    showSettingsDialog(this@requestInitialPermissions, message)
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: List<PermissionRequest>,
                token: PermissionToken
            ) {
                token.continuePermissionRequest()
            }
        }).withErrorListener {
            makeToast("Error occurred while permission handling! ")
        }
        .onSameThread()
        .check()
}

fun Activity.requestCameraPermission(onPermissionGranted: () -> Unit) {
    val activity: Activity = this
    Dexter.withActivity(this)
        .withPermission(Manifest.permission.CAMERA)
        .withListener(object : PermissionListener {
            override fun onPermissionGranted(response: PermissionGrantedResponse) {
                onPermissionGranted()
            }

            override fun onPermissionDenied(response: PermissionDeniedResponse) {
                if (response.isPermanentlyDenied) {
                    showSettingsDialog(
                        activity,
                        "This app needs CAMERA permission to use this feature." +
                                " You can grant them in app settings."
                    )
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                permission: PermissionRequest,
                token: PermissionToken
            ) {
                token.continuePermissionRequest()
            }
        }).check()
}

fun Activity.requestLocationPermission(onPermissionGranted: () -> Unit) {
    val activity: Activity = this
    Dexter.withActivity(this)
        .withPermissions(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        .withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                if (report.areAllPermissionsGranted())
                    onPermissionGranted()

                if (report.isAnyPermissionPermanentlyDenied) {
                    showSettingsDialog(
                        activity,
                        "This app needs permissions to use this feature. " +
                                "You can grant them in app settings."
                    )
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: List<PermissionRequest>,
                token: PermissionToken
            ) {
                token.continuePermissionRequest()
            }
        }).withErrorListener {
            Toast.makeText(
                applicationContext,
                "Error occurred while permission handling! ",
                Toast.LENGTH_SHORT
            ).show()
        }
        .onSameThread()
        .check()
}

fun Activity.requestCameraStoragePermission(onPermissionGranted: () -> Unit) {
    val activity: Activity = this
    Dexter.withActivity(this)
        .withPermissions(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        .withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                if (report.areAllPermissionsGranted())
                    onPermissionGranted()

                if (report.isAnyPermissionPermanentlyDenied) {
                    showSettingsDialog(
                        activity,
                        "This app needs CAMERA and STORAGE permission to use this feature." +
                                " You can grant them in app settings."
                    )
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: List<PermissionRequest>,
                token: PermissionToken
            ) {
                token.continuePermissionRequest()
            }
        }).withErrorListener {
            Toast.makeText(
                applicationContext,
                "Error occurred while permission handling! ",
                Toast.LENGTH_SHORT
            ).show()
        }
        .onSameThread()
        .check()
}

fun Activity.requestStoragePermission(onPermissionGranted: () -> Unit) {
    val activity: Activity = this
    Dexter.withActivity(this)
        .withPermissions(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        .withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                if (report.areAllPermissionsGranted()) {
                    onPermissionGranted()
                }
                if (report.isAnyPermissionPermanentlyDenied) {
                    showSettingsDialog(
                        activity,
                        "This app needs STORAGE permission to use this feature." +
                                " You can grant them in app settings."
                    )
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: List<PermissionRequest>,
                token: PermissionToken
            ) {
                token.continuePermissionRequest()
            }
        }).withErrorListener {
            makeToast("Error occurred while permission handling! ")
        }
        .onSameThread()
        .check()
}

private fun showSettingsDialog(activity: Activity, message: String) {
    val builder = AlertDialog.Builder(activity)
    builder.setTitle(activity.getString(R.string.dialog_permission_title))
    builder.setMessage(message)
    builder.setPositiveButton(activity.getString(R.string.go_to_settings))
    { dialog, _ ->
        dialog.cancel()
        openSettings(activity)
    }
    builder.setNegativeButton(activity.getString(android.R.string.cancel))
    { dialog, _ -> dialog.cancel() }
    builder.show()

}

private fun openSettings(activity: Activity) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", activity.packageName, null)
    intent.data = uri
    activity.startActivityForResult(intent, 101)
}