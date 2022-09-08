package br.com.breaktheice.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import br.com.breaktheice.commons.utility.createAdapter
import br.com.breaktheice.databinding.FragmentActivityListBinding
import br.com.breaktheice.presentation.adapter.MainAdapter
import br.com.breaktheice.presentation.fragment.base.BaseFragment
import br.com.breaktheice.presentation.navigateFromListToDetail
import br.com.breaktheice.presentation.navigateFromListToFilter
import br.com.breaktheice.presentation.state.MainUiState
import kotlinx.coroutines.launch

/**
 * @author Raphael Santos
 */
class ActivityListFragment : BaseFragment() {

    private val mainAdapter by lazy {
        MainAdapter(
            { activityModel ->
                navigateFromListToDetail(activityModel._id)
            },
            { id, favorite ->
                viewModel.updateActivityFavorite(id, favorite)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        bundle: Bundle?
    ): View {
        val binding: FragmentActivityListBinding = FragmentActivityListBinding.inflate(layoutInflater)
        binding.lifecycleOwner = fragmentActivity
        binding.viewModel = viewModel
        binding.newActivityFab.setOnClickListener {
            navigateFromListToFilter()
        }
        binding.recyclerView.createAdapter(fragmentActivity.applicationContext, mainAdapter)

        fetchUiState()

        viewModel.getActivities()

        return binding.root
    }

    private fun fetchUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is MainUiState.GetActivities -> {
                        mainAdapter.replaceList(uiState.activities)
                    }
                    is MainUiState.CallActivity -> {
                        viewModel.insertActivity(uiState.activityModel)
                    }
                    is MainUiState.CallActivityFiltered -> {
                        viewModel.insertActivity(uiState.activityModel)
                    }
                    is MainUiState.DeleteActivity,
                    is MainUiState.InsertActivity,
                    is MainUiState.UpdateActivity,
                    is MainUiState.UpdateActivityFavorite -> {
                        viewModel.getActivities()
                    }
                    else -> {
                        // Do nothing.
                    }
                }
            }
        }
    }
}
