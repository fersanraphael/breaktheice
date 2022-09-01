package br.com.breaktheice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.breaktheice.commons.Result
import br.com.breaktheice.domain.entity.ActivityModel
import br.com.breaktheice.domain.usecase.*
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
    private val deleteActivityUseCase: DeleteActivityUseCase,
    private val doActivityFilteredUseCase: DoActivityFilteredUseCase,
    private val doActivityUseCase: DoActivityUseCase,
    private val getActivitiesUseCase: GetActivitiesUseCase,
    private val getActivityUseCase: GetActivityUseCase,
    private val insertActivityUseCase: InsertActivityUseCase
) : ViewModel() {

    private val uiScope: CoroutineScope = viewModelScope.plus(Dispatchers.Main)

    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState.Idle)

    val uiState: StateFlow<MainUiState>
        get() = _uiState.asStateFlow()

    fun deleteActivity(activityModel: ActivityModel) {
        uiScope.launch {
            deleteActivityUseCase(activityModel).collect { result ->
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

    fun doActivity() {
        uiScope.launch {
            doActivityUseCase().collect { result ->
                _uiState.value = when (result) {
                    is Result.Success -> {
                        MainUiState.DoActivity(result.value)
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

    fun doActivityFiltered(options: MutableMap<String, String>) {
        uiScope.launch {
            doActivityFilteredUseCase(options).collect { result ->
                _uiState.value = when (result) {
                    is Result.Success -> {
                        MainUiState.DoActivityFiltered(result.value)
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
            getActivitiesUseCase().collect { result ->
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

    fun getActivity(id: Int) {
        uiScope.launch {
            getActivityUseCase(id).collect { result ->
                _uiState.value = when (result) {
                    is Result.Success -> {
                        MainUiState.GetActivity(result.value)
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
            insertActivityUseCase(activityModel).collect { result ->
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
}
