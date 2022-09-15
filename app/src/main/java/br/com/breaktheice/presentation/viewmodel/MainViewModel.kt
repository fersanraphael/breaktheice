package br.com.breaktheice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.breaktheice.domain.common.Interactor
import br.com.breaktheice.domain.common.Result
import br.com.breaktheice.domain.entity.ActivityModel
import br.com.breaktheice.presentation.state.MainUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

/**
 * @author Raphael Santos
 */
class MainViewModel constructor(
    private val interactor: Interactor
) : ViewModel() {

    private val uiScope: CoroutineScope = viewModelScope.plus(Dispatchers.Main)

    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState.Idle)

    val uiState: StateFlow<MainUiState>
        get() = _uiState.asStateFlow()

    fun callActivity() {
        uiScope.launch {
            interactor.callActivityUseCase().collect { result ->
                _uiState.value = when (result) {
                    is Result.Success -> {
                        MainUiState.CallActivity(result.value)
                    }
                    is Result.Failure, is Result.Error -> {
                        MainUiState.Error
                    }
                    is Result.Loading -> {
                        MainUiState.Loading
                    }
                }
            }
        }
    }

    fun callActivityFiltered(options: MutableMap<String, String>) {
        uiScope.launch {
            interactor.callActivityFilteredUseCase(options).collect { result ->
                _uiState.value = when (result) {
                    is Result.Success -> {
                        MainUiState.CallActivityFiltered(result.value)
                    }
                    is Result.Failure, is Result.Error -> {
                        MainUiState.Error
                    }
                    is Result.Loading -> {
                        MainUiState.Loading
                    }
                }
            }
        }
    }

    fun deleteActivity(activityModel: ActivityModel) {
        uiScope.launch {
            interactor.deleteActivityUseCase(activityModel).collect { result ->
                _uiState.value = when (result) {
                    is Result.Success -> {
                        MainUiState.DeleteActivity
                    }
                    is Result.Failure, is Result.Error -> {
                        MainUiState.Error
                    }
                    is Result.Loading -> {
                        MainUiState.Loading
                    }
                }
            }
        }
    }

    fun getActivitiesByType(type: String) {
        uiScope.launch {
            interactor.getActivitiesByTypeUseCase(type).collect { result ->
                _uiState.value = when (result) {
                    is Result.Success -> {
                        MainUiState.GetActivitiesByType(result.value)
                    }
                    is Result.Failure, is Result.Error -> {
                        MainUiState.Error
                    }
                    is Result.Loading -> {
                        MainUiState.Loading
                    }
                }
            }
        }
    }

    fun getActivities() {
        uiScope.launch {
            interactor.getActivitiesUseCase().collect { result ->
                _uiState.value = when (result) {
                    is Result.Success -> {
                        MainUiState.GetActivities(result.value)
                    }
                    is Result.Failure, is Result.Error -> {
                        MainUiState.Error
                    }
                    is Result.Loading -> {
                        MainUiState.Loading
                    }
                }
            }
        }
    }

    fun getActivityById(id: Int) {
        uiScope.launch {
            interactor.getActivityByIdUseCase(id).collect { result ->
                _uiState.value = when (result) {
                    is Result.Success -> {
                        MainUiState.GetActivityById(result.value)
                    }
                    is Result.Failure, is Result.Error -> {
                        MainUiState.Error
                    }
                    is Result.Loading -> {
                        MainUiState.Loading
                    }
                }
            }
        }
    }

    fun insertActivity(activityModel: ActivityModel) {
        uiScope.launch {
            interactor.insertActivityUseCase(activityModel).collect { result ->
                _uiState.value = when (result) {
                    is Result.Success -> {
                        MainUiState.InsertActivity
                    }
                    is Result.Failure, is Result.Error -> {
                        MainUiState.Error
                    }
                    is Result.Loading -> {
                        MainUiState.Loading
                    }
                }
            }
        }
    }

    fun updateActivityFavorite(
        id: Int,
        favorite: Boolean
    ) {
        uiScope.launch {
            interactor.updateActivityFavoriteUseCase(id, favorite).collect { result ->
                _uiState.value = when (result) {
                    is Result.Success -> {
                        MainUiState.UpdateActivityFavorite
                    }
                    is Result.Failure, is Result.Error -> {
                        MainUiState.Error
                    }
                    is Result.Loading -> {
                        MainUiState.Loading
                    }
                }
            }
        }
    }

    fun updateActivity(activityModel: ActivityModel) {
        uiScope.launch {
            interactor.updateActivityUseCase(activityModel).collect { result ->
                _uiState.value = when (result) {
                    is Result.Success -> {
                        MainUiState.UpdateActivity
                    }
                    is Result.Failure, is Result.Error -> {
                        MainUiState.Error
                    }
                    is Result.Loading -> {
                        MainUiState.Loading
                    }
                }
            }
        }
    }
}
