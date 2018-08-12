package pl.karollisiewicz.common.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar

infix fun View.isVisibleWhen(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}