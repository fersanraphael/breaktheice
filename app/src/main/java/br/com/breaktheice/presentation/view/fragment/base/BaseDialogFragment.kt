package br.com.breaktheice.presentation.view.fragment.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import br.com.breaktheice.R
import br.com.breaktheice.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * @author Raphael Santos
 */
open class BaseDialogFragment : DialogFragment() {

    protected val fragmentActivity: FragmentActivity by lazy {
        requireActivity()
    }
    protected val fragmentContext: Context by lazy {
        requireContext()
    }
    protected val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateDialog(bundle: Bundle?): Dialog {
        setStyle(STYLE_NORMAL, R.style.Theme_Appbreaktheice_Dialog)

        return super.onCreateDialog(bundle)
    }
}
