package br.com.breaktheice.util.binding

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import br.com.breaktheice.util.getAccessibilityText

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
    @BindingAdapter("app:accessibilityText")
    fun TextView.setAccessibilityText(accessibility: Float) {
        text = context.getAccessibilityText(accessibility)
    }
}
