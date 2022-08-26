package br.com.bravi.breaktheice.presentation.fragment.base

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import br.com.bravi.breaktheice.R
import br.com.bravi.breaktheice.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * @author Raphael Santos
 */
open class BaseDialogFragment : DialogFragment() {

    protected val fragmentActivity by lazy {
        requireActivity()
    }
    protected val fragmentContext by lazy {
        requireContext()
    }
    protected val viewModel by sharedViewModel<MainViewModel>()

    override fun onCreateDialog(bundle: Bundle?): Dialog {
        setStyle(STYLE_NORMAL, R.style.Theme_Dialog)

        return super.onCreateDialog(bundle)
    }
}
