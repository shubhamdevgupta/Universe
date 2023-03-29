package com.example.alankituniverse.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.airbnb.lottie.LottieAnimationView
import com.example.alankituniverse.R
import com.example.alankituniverse.util.extns.LottieType
import com.example.alankituniverse.util.extns.set
import com.example.alankituniverse.util.extns.textColor
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*

object AppDialog {

    fun progress(
        context: Context, title: String = "Loading...", cancelable: Boolean = false
    ): Dialog {
        val dialog = dialogConfiguration(
            context, R.layout.dialog_progress, wrapWith = true, cancelable = cancelable
        )
        dialog.findViewById<TextView>(R.id.tv_title).text = title
        dialog.show()
        return dialog
    }

    fun updateDialog(context: Context): Dialog {
        return dialogConfiguration(
            context, R.layout.update_dialog, false
        )
    }

    fun confirm(
        activity: Activity, description: String, onPositiveClick: () -> Unit
    ): androidx.appcompat.app.AlertDialog? {

        return MaterialAlertDialogBuilder(activity).setTitle("Confirm").setMessage(description)
            .setPositiveButton(
                "ok"
            ) { dialog, which ->
                onPositiveClick()
            }.setNegativeButton("Cancel", null).show()
    }

    fun success(context: Activity, message: String, goBack: Boolean = false): Dialog =
        statusDialog(context, message, STATUS.SUCCESS, goBack)

    fun failed(context: Activity, message: String, goBack: Boolean = false): Dialog =
        statusDialog(context, message, STATUS.FAILED, goBack)

    fun pending(context: Activity, message: String, goBack: Boolean = false): Dialog =
        statusDialog(context, message, STATUS.PENDING, goBack)


    private fun statusDialog(
        context: Activity, message: String, type: STATUS, goBack: Boolean
    ): Dialog {
        val dialog = dialogConfiguration(context, R.layout.dialog_response_status, wrapWith = false)
        val tvMessage = dialog.findViewById<TextView>(R.id.tv_message)
        val lottieView = dialog.findViewById<LottieAnimationView>(R.id.lottie_view)
        val btnDone = dialog.findViewById<Button>(R.id.btn_done)

        tvMessage.text = message

        btnDone.setOnClickListener { dialog.dismiss() }
        dialog.setOnDismissListener {
            if (goBack) context.onBackPressed()
        }

        when (type) {
            STATUS.SUCCESS -> {
                tvMessage.textColor(context, R.color.green)
                lottieView.set(LottieType.SUCCESS)
            }
            STATUS.FAILED -> {
                tvMessage.textColor(context, R.color.red)
                lottieView.set(LottieType.FAILED)
            }
            STATUS.PENDING -> {
                tvMessage.textColor(context, R.color.black)
                lottieView.set(LottieType.PENDING)
            }
        }
        dialog.show()
        return dialog
    }

    enum class STATUS {
        SUCCESS, FAILED, PENDING
    }

    fun openCameraGallery(context: Context): Dialog {
        return dialogConfiguration(
            context, R.layout.dialog_open_camera_gallery
        )
    }
}

fun dialogConfiguration(
    context: Context, layout: Int, cancelable: Boolean = true, wrapWith: Boolean = false
): Dialog {
    val dialog = Dialog(context)
    dialog.setContentView(layout)
    dialog.setCanceledOnTouchOutside(false)
    dialog.setCancelable(cancelable)
    val mainView: CardView = dialog.findViewById(R.id.card_view)
    val displayMetrics = context.resources.displayMetrics
    val paramsLcl = mainView.layoutParams as FrameLayout.LayoutParams
    if (!wrapWith) {
        val widthLcl = (displayMetrics.widthPixels * 0.90f).toInt()
        paramsLcl.width = widthLcl
    }
    paramsLcl.gravity = Gravity.CENTER
    mainView.layoutParams = paramsLcl
    Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(
        ColorDrawable(Color.TRANSPARENT)
    )
    if (!dialog.isShowing) dialog.show()
    return dialog
}