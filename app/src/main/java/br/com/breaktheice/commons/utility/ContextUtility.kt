package br.com.breaktheice.commons.utility

import android.content.Context
import br.com.breaktheice.R

/**
 * @author Raphael Santos
 */

fun Context.getAccessibilityText(accessibility: Float): String {
    return when (accessibility) {
        in 0.0..0.29 -> {
            getString(R.string.label_accessibility_easy)
        }
        in 0.3..0.59 -> {
            getString(R.string.label_accessibility_medium)
        }
        else -> {
            getString(R.string.label_accessibility_hard)
        }
    }
}
