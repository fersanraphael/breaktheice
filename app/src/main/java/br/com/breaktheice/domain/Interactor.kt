package br.com.breaktheice.domain

import br.com.breaktheice.domain.usecase.*

/**
 * @author Raphael Santos
 */
data class Interactor constructor(
    val callActivityFilteredUseCase: CallActivityFilteredUseCase,
    val callActivityUseCase: CallActivityUseCase,
    val deleteActivityUseCase: DeleteActivityUseCase,
    val getActivitiesUseCase: GetActivitiesUseCase,
    val getActivityByIdUseCase: GetActivityByIdUseCase,
    val getActivitiesByTypeUseCase: GetActivitiesByTypeUseCase,
    val insertActivityUseCase: InsertActivityUseCase,
    val updateActivityFavoriteUseCase: UpdateActivityFavoriteUseCase,
    val updateActivityUseCase: UpdateActivityUseCase
)
