package com.example.alankituniverse.util.extns

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.alankituniverse.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.alphaFull() {
    this.alpha = 1f
}

fun View.alphaHalf() {
    this.alpha = 0.5f
}


fun viewEnable(vararg views: View, value: Boolean) {
    views.forEach {
        if (value) {
            it.alphaFull()
            it.isEnabled = true
        } else {
            it.alphaHalf()
            it.isEnabled = false
        }
    }
}

fun TextView.textColor(context: Context, color: Int) {
    this.setTextColor(ContextCompat.getColor(context, color))
}


fun LottieAnimationView.set(type: LottieType) {
    val fileName = when (type) {
        LottieType.ALERT -> "lottie_alert.json"
        LottieType.SUCCESS -> "lottie_success.json"
        LottieType.FAILED -> "lottie_failed.json"
        LottieType.PENDING -> "lottie_pending.json"
        LottieType.WARNING -> "lottie_warning.json"
        LottieType.TIME_OUT -> "lottie_time_out.json"
        LottieType.NO_INTERNET -> "lottie_no_internet.json"
        LottieType.SERVER -> "lottie_server.json"
    }

    this.setAnimation(fileName)
    this.playAnimation()
}

enum class LottieType {
    SUCCESS, FAILED, PENDING, ALERT, WARNING, NO_INTERNET, SERVER, TIME_OUT
}


fun ViewGroup.setChildAlpha(
    visibility: Boolean = true,
    exceptView: View? = null,
    alpha: Float = 0.5f
) {
    EnableWithAlpha(this, visibility, exceptView, alpha)
}


private class EnableWithAlpha(
    viewGroup: ViewGroup,
    visibility: Boolean,
    exceptView: View?,
    alpha: Float
) {
    init {
        viewGroup.forEach { thisChild ->
            when (thisChild) {
                is CardView -> disablingCardView(visibility, thisChild, exceptView, alpha)
                is RecyclerView -> {
                    if (visibility) thisChild.show()
                    else thisChild.hide()
                }
                is ViewGroup -> {
                    exceptView?.let {
                        if (thisChild.id != it.id)
                            thisChild.setChildAlpha(
                                visibility = visibility,
                                exceptView = exceptView,
                                alpha = alpha
                            )
                    } ?: thisChild.setChildAlpha(
                        visibility = visibility,
                        exceptView = null,
                        alpha = alpha
                    )
                }
                else -> {
                    exceptView?.let {
                        if (thisChild.id != it.id)
                            setVisibility(visibility, thisChild, alpha)
                    } ?: run {
                        setVisibility(visibility, thisChild, alpha)
                    }
                }
            }
        }
    }

    private fun disablingCardView(
        visibility: Boolean,
        cardView: CardView,
        exceptView: View?,
        alpha: Float
    ) {
        if (cardView.childCount == 0)
            exceptView?.let { _ ->
                if (cardView.id != exceptView.id)
                    setVisibility(visibility, cardView, alpha)
            } ?: run {
                setVisibility(visibility, cardView, alpha)
            }
        else if (cardView.childCount == 1) {
            cardView.forEach { cardViewChild ->
                if (cardViewChild is ViewGroup) {
                    exceptView?.let { _ ->
                        if (cardViewChild.id != exceptView.id)
                            cardViewChild.setChildAlpha(
                                visibility = visibility,
                                exceptView = exceptView,
                                alpha = alpha
                            )
                    } ?: cardViewChild.setChildAlpha(
                        visibility = visibility,
                        exceptView = null,
                        alpha = alpha
                    )
                } else {
                    exceptView?.let { _ ->
                        if (cardViewChild.id != exceptView.id)
                            setVisibility(visibility, cardViewChild, alpha)
                    } ?: run {
                        setVisibility(visibility, cardViewChild, alpha)
                    }
                }
            }
        }
    }

    fun getCurrentDate() {
        val sdf = SimpleDateFormat("yyyy/mm/dd")
        val currentDate = sdf.format(Date())
    }


    private fun setVisibility(value: Boolean, view: View, alpha: Float) {
        if (value) {
            view.alpha = 1f
            view.isEnabled = true
        } else {
            view.alpha = alpha
            view.isEnabled = false
        }
    }
}

//SETTING UP IMAGE INTO GLIDE

fun ImageView.setGlideImage(imgUrl: String?, delaySecond: Int = 0) {

    val imageView: ImageView = this


    if (imgUrl != null) {
        Glide.with(this.context)
            .load(imgUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {

                    GlobalScope.launch(Dispatchers.Main) {
                        delay(delaySecond.toLong() * 1000)
                        imageView.setImageDrawable(
                            ContextCompat.getDrawable(
                                imageView.context,
                                R.drawable.no_photo
                            )
                        )
                    }
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    GlobalScope.launch(Dispatchers.Main) {
                        delay(delaySecond.toLong() * 1000)
                        imageView.setImageDrawable(resource)
                    }

                    return true
                }

            }).submit()
    }
}
/*

fun Spinner.setAdapterEntries(entries: List<String>, onItemSelected: (value: String) -> Unit)
        : ArrayAdapter<String> {

    val spinner = this
    val adapter = ArrayAdapter(context, R.layout.spinner_layout, entries)
    adapter.setDropDownViewResource(R.layout.spinner_layout_dropdown)
    this.adapter = adapter
    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            onItemSelected(spinner.selectedItem.toString())
        }

    }
    return adapter

}*/
