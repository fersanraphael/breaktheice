package br.com.bravi.breaktheice.presentation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.bravi.breaktheice.presentation.fragment.ActivityListFragmentDirections
import br.com.bravi.breaktheice.presentation.fragment.base.BaseDialogFragment
import br.com.bravi.breaktheice.presentation.fragment.base.BaseFragment

/**
 * @author Raphael Santos
 */

fun BaseFragment.navigateFromListToDetail(activityId: Int) {
    findNavController().navigate(ActivityListFragmentDirections.actionListToDetail(activityId))
}

fun BaseFragment.navigateFromListToFilter() {
    findNavController().navigate(ActivityListFragmentDirections.actionListToFilter())
}

fun Fragment.popBack() {
    findNavController().popBackStack()
}
