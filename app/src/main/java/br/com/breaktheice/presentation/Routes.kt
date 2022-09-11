package br.com.breaktheice.presentation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.breaktheice.presentation.fragment.ActivityHomeFragmentDirections
import br.com.breaktheice.presentation.fragment.ActivityListFragmentDirections
import br.com.breaktheice.presentation.fragment.base.BaseFragment

/**
 * @author Raphael Santos
 */

fun BaseFragment.navigateFromHomeToDetail(activityId: Int) {
    findNavController().navigate(ActivityHomeFragmentDirections.actionHomeToDetail(activityId))
}

fun BaseFragment.navigateFromHomeToList() {
    findNavController().navigate(ActivityHomeFragmentDirections.actionHomeToList())
}

fun BaseFragment.navigateFromListToDetail(activityId: Int) {
    findNavController().navigate(ActivityListFragmentDirections.actionListToDetail(activityId))
}

fun BaseFragment.navigateFromListToFilter() {
    findNavController().navigate(ActivityListFragmentDirections.actionListToFilter())
}

fun Fragment.popBack() {
    findNavController().popBackStack()
}
