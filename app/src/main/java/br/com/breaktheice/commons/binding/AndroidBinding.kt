package br.com.breaktheice.commons.binding

import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import br.com.breaktheice.R
import br.com.breaktheice.commons.utility.getAccessibilityText

/**
 * @author Raphael Santos
 */
object AndroidBinding {

    @JvmStatic
    @BindingAdapter("android:visibility")
    fun View.setVisibility(value: Boolean) {
        visibility = if (value) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("accessibilityText")
    fun AppCompatTextView.setAccessibilityText(accessibility: Float) {
        text = context.getAccessibilityText(accessibility)
    }

    @JvmStatic
    @BindingAdapter("capitalizeFirstCharText")
    fun AppCompatTextView.setCapitalizeFirstCharText(type: String) {
        text = type.replaceFirstChar { char -> char.uppercase() }
    }

    @JvmStatic
    @BindingAdapter("srcType")
    fun AppCompatImageView.setSrcType(type: String) {
        @DrawableRes val resId: Int = when (type) {
            "education" -> R.drawable.ic_education
            "recreational" -> R.drawable.ic_recreational
            "social" -> R.drawable.ic_social
            "diy" -> R.drawable.ic_diy
            "charity" -> R.drawable.ic_charity
            "cooking" -> R.drawable.ic_cooking
            "relaxation" -> R.drawable.ic_relaxation
            "music" -> R.drawable.ic_music
            else -> R.drawable.ic_busywork
        }
        setImageDrawable(ResourcesCompat.getDrawable(resources, resId, context.theme))
    }
}
