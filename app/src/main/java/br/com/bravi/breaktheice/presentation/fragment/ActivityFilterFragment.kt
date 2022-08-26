package br.com.bravi.breaktheice.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.bravi.breaktheice.R
import br.com.bravi.breaktheice.databinding.FragmentActivityFilterBinding
import br.com.bravi.breaktheice.presentation.fragment.base.BaseDialogFragment
import br.com.bravi.breaktheice.presentation.popBack
import br.com.bravi.breaktheice.util.constant.WEBSERVICE_QUERY_TYPE
import br.com.bravi.breaktheice.util.createAdapter
import java.util.Locale.ROOT

/**
 * @author Raphael Santos
 */
class ActivityFilterFragment : BaseDialogFragment() {

    private val activityTypeArr by lazy {
        resources.getStringArray(R.array.activity_type_array)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        bundle: Bundle?
    ): View {
        val binding: FragmentActivityFilterBinding = FragmentActivityFilterBinding.inflate(layoutInflater)
        binding.lifecycleOwner = fragmentActivity
        binding.viewModel = viewModel
        binding.searchButton.setOnClickListener {
            val queries: MutableMap<String, String> = mutableMapOf()
            val activityTypeStr: String = binding.typeAutoCompleteTextView.text.toString()
            if (activityTypeStr.equals(activityTypeArr[0], ignoreCase = true)) {
                viewModel.doActivity()
            } else {
                queries[WEBSERVICE_QUERY_TYPE] = activityTypeStr.lowercase(ROOT)
                viewModel.doActivityFiltered(queries)
            }

            popBack()
        }

        binding.typeAutoCompleteTextView.createAdapter(fragmentContext, activityTypeArr)

        return binding.root
    }
}
