package br.com.breaktheice.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.breaktheice.R
import br.com.breaktheice.data.utility.WEBSERVICE_QUERY_TYPE
import br.com.breaktheice.databinding.FragmentActivityFilterBinding
import br.com.breaktheice.presentation.popBack
import br.com.breaktheice.presentation.utility.createAdapter
import br.com.breaktheice.presentation.view.fragment.base.BaseDialogFragment

/**
 * @author Raphael Santos
 */
class ActivityFilterFragment : BaseDialogFragment() {

    private val activityTypeArr: Array<out String> by lazy {
        resources.getStringArray(R.array.activity_array)
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
            val activityTypeText: String = binding.typeAutoCompleteTextView.text.toString()
            if (activityTypeText.equals(activityTypeArr.first(), true)) {
                viewModel.callActivity()
            } else {
                val queries: MutableMap<String, String> = mutableMapOf()
                queries[WEBSERVICE_QUERY_TYPE] = activityTypeText.lowercase()
                viewModel.callActivityFiltered(queries)
            }

            popBack()
        }
        binding.typeAutoCompleteTextView.createAdapter(fragmentContext, activityTypeArr)

        return binding.root
    }
}
