package br.com.breaktheice.presentation.state

import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
sealed interface MainUiState {

    data class CallActivity(
        val activityModel: ActivityModel
    ) : MainUiState

    data class CallActivityFiltered(
        val activityModel: ActivityModel
    ) : MainUiState

    object DeleteActivity : MainUiState

    object Error : MainUiState

    data class GetActivities(
        val activities: MutableList<ActivityModel>
    ) : MainUiState

    data class GetActivity(
        val activityModel: ActivityModel
    ) : MainUiState

    object InsertActivity : MainUiState

    object Idle : MainUiState

    object Loading : MainUiState
}
