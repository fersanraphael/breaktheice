package br.com.breaktheice.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import br.com.breaktheice.R
import br.com.breaktheice.commons.utility.createAdapter
import br.com.breaktheice.databinding.FragmentActivityListBinding
import br.com.breaktheice.presentation.adapter.ActivityAdapter
import br.com.breaktheice.presentation.adapter.ActivityTypeAdapter
import br.com.breaktheice.presentation.fragment.base.BaseFragment
import br.com.breaktheice.presentation.navigateFromListToDetail
import br.com.breaktheice.presentation.navigateFromListToFilter
import br.com.breaktheice.presentation.state.MainUiState
import kotlinx.coroutines.launch

/**
 * @author Raphael Santos
 */
class ActivityListFragment : BaseFragment() {

    private val activityAdapter by lazy {
        ActivityAdapter(
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
        binding.activityRecyclerView.createAdapter(
            fragmentActivity.applicationContext,
            activityAdapter
        )
        binding.activityTypeRecyclerView.createAdapter(
            fragmentActivity.applicationContext,
            ActivityTypeAdapter { activityType ->
                // TODO
            }.apply {
                replaceList(resources.getStringArray(R.array.activity_type_array).toMutableList())
            },
            orientation = RecyclerView.HORIZONTAL,
            spanCount = 1,
            attachSnapHelper = true
        )

        fetchUiState()

        viewModel.getActivities()

        return binding.root
    }

    private fun fetchUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is MainUiState.GetActivities -> {
                        activityAdapter.replaceList(uiState.activities.subList(0, uiState.activities.size.coerceAtMost(3)))
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
