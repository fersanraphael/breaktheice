package br.com.breaktheice.commons.binding

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
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
    fun TextView.setAccessibilityText(accessibility: Float) {
        text = context.getAccessibilityText(accessibility)
    }

    @JvmStatic
    @BindingAdapter("capFirstCharText")
    fun TextView.setCapFirstCharText(type: String) {
        text = type.replaceFirstChar { char -> char.uppercase() }
    }
}
