package br.com.breaktheice.util

import android.content.Context
import android.content.res.Configuration
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import br.com.breaktheice.R

/**
 * @author Raphael Santos
 */

private fun Context.isOrientationPortrait(): Boolean {
    return resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
}

fun RecyclerView.createAdapter(
    context: Context,
    viewAdapter: RecyclerView.Adapter<*>,
    itemDecoration: ItemDecoration? = null,
    orientation: Int = RecyclerView.VERTICAL
) {
    val linearLayoutManager = LinearLayoutManager(context, orientation, false)
    val gridLayoutManager = GridLayoutManager(context, 2)
    adapter = viewAdapter
    layoutManager = if (context.isOrientationPortrait()) {
        linearLayoutManager
    } else {
        gridLayoutManager
    }

    setHasFixedSize(false)

    if (itemDecoration != null) {
        addItemDecoration(itemDecoration)
    }
}

fun <T> AutoCompleteTextView.createAdapter(
    context: Context,
    stringArray: Array<T>
) {
    val arrayAdapter: ArrayAdapter<T> = ArrayAdapter(context, R.layout.dropdown_item, stringArray)
    setAdapter(arrayAdapter)
}
