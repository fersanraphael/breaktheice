package br.com.breaktheice.presentation.view.fragment.base

import androidx.fragment.app.Fragment
import br.com.breaktheice.R
import br.com.breaktheice.presentation.viewmodel.MainViewModel
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * @author Raphael Santos
 */
open class BaseFragment : Fragment() {

    protected val fragmentActivity by lazy {
        requireActivity()
    }
    protected val viewModel by sharedViewModel<MainViewModel>()

    fun setAppBarTitle(title: String) {
        val appBarLayout = fragmentActivity.findViewById<CollapsingToolbarLayout?>(R.id.app_bar_layout) ?: return
        appBarLayout.title = title
    }
}
