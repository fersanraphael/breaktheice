package br.com.breaktheice.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import br.com.breaktheice.R
import br.com.breaktheice.data.common.constant.WEBSERVICE_QUERY_TYPE
import br.com.breaktheice.databinding.FragmentActivityListBinding
import br.com.breaktheice.domain.entity.ActivityModel
import br.com.breaktheice.presentation.adapter.ActivityAdapter
import br.com.breaktheice.presentation.common.utility.createAdapter
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
                navigateFromListToDetail(activityModel.id)
            },
            { id, favorite ->
                viewModel.updateActivityFavorite(id, favorite)
            }
        )
    }
    private val arguments by navArgs<ActivityListFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        bundle: Bundle?
    ): View {
        val binding: FragmentActivityListBinding = FragmentActivityListBinding.inflate(layoutInflater)
        binding.lifecycleOwner = fragmentActivity
        binding.viewModel = viewModel
        binding.activityRecyclerView.createAdapter(
            fragmentActivity.applicationContext,
            activityAdapter
        )
        binding.newActivityFab.setOnClickListener {
            checkArgs({ activityType ->
                val queries: MutableMap<String, String> = mutableMapOf()
                queries[WEBSERVICE_QUERY_TYPE] = activityType.lowercase()
                viewModel.callActivityFiltered(queries)
            }, {
                navigateFromListToFilter()
            })
        }

        fetchUiState()

        checkArgs({ activityType ->
            setAppBarTitle(getString(R.string.fragment_activity_type_list, activityType))
            viewModel.getActivitiesByType(activityType.lowercase())
        }, {
            setAppBarTitle(getString(R.string.fragment_activity_list))
            viewModel.getActivities()
        })

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
                            )
                        )
                    }
                    is MainUiState.GetActivitiesByType -> {
                        activityAdapter.replaceList(
                            uiState.activities.sortedWith(
                                compareBy<ActivityModel> { activityModel -> activityModel.favorite }.reversed()
                            )
                        )
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
                        checkArgs({ activityType ->
                            viewModel.getActivitiesByType(activityType.lowercase())
                        }, {
                            viewModel.getActivities()
                        })
                    }
                    else -> {
                        // Do nothing.
                    }
                }
            }
        }
    }

    private fun checkArgs(
        queryType: (String) -> Unit,
        queryAll: () -> Unit
    ) {
        val activityType: String? = arguments.activityType
        if (activityType != null && activityType.isNotEmpty()) {
            queryType.invoke(activityType)
        } else {
            queryAll.invoke()
        }
    }
}
