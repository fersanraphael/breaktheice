package br.com.breaktheice.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import br.com.breaktheice.R
import br.com.breaktheice.databinding.FragmentActivityHomeBinding
import br.com.breaktheice.domain.entity.ActivityModel
import br.com.breaktheice.presentation.navigateFromHomeToDetail
import br.com.breaktheice.presentation.navigateFromHomeToList
import br.com.breaktheice.presentation.state.MainUiState
import br.com.breaktheice.presentation.util.utility.createAdapter
import br.com.breaktheice.presentation.view.adapter.ActivityAdapter
import br.com.breaktheice.presentation.view.adapter.ActivityTypeAdapter
import br.com.breaktheice.presentation.view.fragment.base.BaseFragment
import kotlinx.coroutines.launch

/**
 * @author Raphael Santos
 */
class ActivityHomeFragment : BaseFragment() {

    private val activityAdapter by lazy {
        ActivityAdapter(
            { activityModel ->
                navigateFromHomeToDetail(activityModel.id)
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
                navigateFromHomeToList(activityType)
            }.apply {
                replaceList(resources.getStringArray(R.array.activity_type_array).toList())
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

        fetchUiState()

        setAppBarTitle(getString(R.string.fragment_activity_home))

        viewModel.getActivities()

        return binding.root
    }

    private fun fetchUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is MainUiState.GetActivities -> {
                        activityAdapter.replaceList(
                            uiState.activities.sortedWith(
                                compareBy<ActivityModel> { activityModel -> activityModel.favorite }.reversed()
                            ).subList(0, uiState.activities.size.coerceAtMost(3))
                        )
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
