package br.com.breaktheice.presentation.view.fragment.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import br.com.breaktheice.R
import br.com.breaktheice.presentation.viewmodel.MainViewModel
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * @author Raphael Santos
 */
open class BaseFragment : Fragment() {

    protected val fragmentActivity: FragmentActivity by lazy {
        requireActivity()
    }
    protected val viewModel: MainViewModel by sharedViewModel()

    fun setAppBarTitle(title: String) {
        val appBarLayout: CollapsingToolbarLayout = fragmentActivity.findViewById(R.id.app_bar_layout) ?: return
        appBarLayout.title = title
    }
}
