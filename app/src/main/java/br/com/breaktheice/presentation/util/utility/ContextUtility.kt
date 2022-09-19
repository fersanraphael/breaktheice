package br.com.breaktheice.presentation.util.utility

import android.content.Context
import android.content.res.Configuration
import br.com.breaktheice.R

/**
 * @author Raphael Santos
 */

fun Context.getAccessibilityText(accessibility: Float): String {
    return when (accessibility) {
        in 0.0..0.19 -> {
            getString(R.string.label_accessibility_very_easy)
        }
        in 0.20..0.39 -> {
            getString(R.string.label_accessibility_easy)
        }
        in 0.40..0.59 -> {
            getString(R.string.label_accessibility_medium)
        }
        in 0.60..0.79 -> {
            getString(R.string.label_accessibility_hard)
        }
        else -> {
            getString(R.string.label_accessibility_very_hard)
        }
    }
}

fun Context.isOrientationPortrait(): Boolean {
    return resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
}
