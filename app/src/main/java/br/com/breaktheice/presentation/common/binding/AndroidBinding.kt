package br.com.breaktheice.presentation.common.binding

import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import br.com.breaktheice.R
import br.com.breaktheice.presentation.common.utility.getAccessibilityText

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
        text = type.replaceFirstChar { char ->
            char.uppercase()
        }
    }

    @JvmStatic
    @BindingAdapter("backgroundType")
    fun View.setBackgroundType(type: String) {
        @DrawableRes val resId: Int = resources.getIdentifier("image_${type.lowercase()}", "drawable", context.packageName)
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
        @DrawableRes val resId: Int = resources.getIdentifier("icon_${type.lowercase()}", "drawable", context.packageName)
        setImageDrawable(ResourcesCompat.getDrawable(resources, resId, context.theme))
    }
}
