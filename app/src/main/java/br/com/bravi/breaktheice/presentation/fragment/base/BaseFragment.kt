package br.com.bravi.breaktheice.presentation.fragment.base

import androidx.fragment.app.Fragment
import br.com.bravi.breaktheice.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * @author Raphael Santos
 */
open class BaseFragment : Fragment() {

    protected val fragmentActivity by lazy {
        requireActivity()
    }
    protected val viewModel by sharedViewModel<MainViewModel>()
}
