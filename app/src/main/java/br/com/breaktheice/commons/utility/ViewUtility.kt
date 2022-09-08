package br.com.breaktheice.commons.utility

import android.content.Context
import android.content.res.Configuration
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.*
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
    orientation: Int = RecyclerView.VERTICAL,
    spanCount: Int = 2,
    attachSnapHelper: Boolean = false
) {
    val linearLayoutManager = LinearLayoutManager(context, orientation, false)
    val gridLayoutManager = GridLayoutManager(context, spanCount)
    adapter = viewAdapter
    layoutManager = if (context.isOrientationPortrait() || orientation == RecyclerView.HORIZONTAL) {
        linearLayoutManager
    } else {
        gridLayoutManager
    }

    setHasFixedSize(false)

    if (itemDecoration != null) {
        addItemDecoration(itemDecoration)
    }

    if (attachSnapHelper) {
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(this)
    }
}

fun <T> AutoCompleteTextView.createAdapter(
    context: Context,
    array: Array<T>
) {
    val arrayAdapter: ArrayAdapter<T> = ArrayAdapter(context, R.layout.dropdown_item, array)
    setAdapter(arrayAdapter)
}
