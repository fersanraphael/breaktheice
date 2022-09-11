package br.com.breaktheice.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import br.com.breaktheice.R
import br.com.breaktheice.commons.utility.createAdapter
import br.com.breaktheice.databinding.FragmentActivityHomeBinding
import br.com.breaktheice.presentation.adapter.ActivityAdapter
import br.com.breaktheice.presentation.adapter.ActivityTypeAdapter
import br.com.breaktheice.presentation.fragment.base.BaseFragment
import br.com.breaktheice.presentation.navigateFromHomeToDetail
import br.com.breaktheice.presentation.navigateFromHomeToList
import br.com.breaktheice.presentation.state.MainUiState
import kotlinx.coroutines.launch

/**
 * @author Raphael Santos
 */
class ActivityHomeFragment : BaseFragment() {

    private val activityAdapter by lazy {
        ActivityAdapter(
            { activityModel ->
                navigateFromHomeToDetail(activityModel._id)
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
        val binding: FragmentActivityHomeBinding = FragmentActivityHomeBinding.inflate(layoutInflater)
        binding.lifecycleOwner = fragmentActivity
        binding.viewModel = viewModel
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
        binding.activityRecyclerView.createAdapter(
            fragmentActivity.applicationContext,
            activityAdapter
        )
        binding.moreActivityButton.setOnClickListener {
            navigateFromHomeToList()
        }

        setAppBarTitle(R.string.fragment_activity_home)

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
