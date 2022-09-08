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
    @BindingAdapter("backgroundType")
    fun View.setBackgroundType(type: String) {
        @DrawableRes val resId: Int = when (type.lowercase()) {
            "education" -> R.drawable.image_education
            "recreational" -> R.drawable.image_recreational
            "social" -> R.drawable.image_social
            "diy" -> R.drawable.image_diy
            "charity" -> R.drawable.image_charity
            "cooking" -> R.drawable.image_cooking
            "relaxation" -> R.drawable.image_relaxation
            "music" -> R.drawable.image_music
            else -> R.drawable.image_busywork
        }
        background = ResourcesCompat.getDrawable(resources, resId, context.theme)
    }

    @JvmStatic
    @BindingAdapter("srcFavorite")
    fun AppCompatImageView.setSrcFavorite(favorite: Boolean) {
        @DrawableRes val resId: Int = if (favorite) {
            R.drawable.icon_star_filled
        } else {
            R.drawable.icon_star_outlined
        }
        setImageDrawable(ResourcesCompat.getDrawable(resources, resId, context.theme))
    }

    @JvmStatic
    @BindingAdapter("srcType")
    fun AppCompatImageView.setSrcType(type: String) {
        @DrawableRes val resId: Int = when (type.lowercase()) {
            "education" -> R.drawable.icon_education
            "recreational" -> R.drawable.icon_recreational
            "social" -> R.drawable.icon_social
            "diy" -> R.drawable.icon_diy
            "charity" -> R.drawable.icon_charity
            "cooking" -> R.drawable.icon_cooking
            "relaxation" -> R.drawable.icon_relaxation
            "music" -> R.drawable.icon_music
            else -> R.drawable.icon_busywork
        }
        setImageDrawable(ResourcesCompat.getDrawable(resources, resId, context.theme))
    }
}
