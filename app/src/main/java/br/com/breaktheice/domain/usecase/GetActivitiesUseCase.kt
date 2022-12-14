package br.com.breaktheice.domain.usecase

import br.com.breaktheice.domain.entity.ActivityModel
import br.com.breaktheice.domain.repository.IActivityRepository
import br.com.breaktheice.domain.utility.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

/**
 * @author Raphael Santos
 */

interface IGetActivitiesUseCase {

    operator fun invoke(): Flow<Result<MutableList<ActivityModel>>>
}

class GetActivitiesUseCaseImpl constructor(
    private val activityRepository: IActivityRepository
) : IGetActivitiesUseCase {

    override operator fun invoke(): Flow<Result<MutableList<ActivityModel>>> {
        return flow {
            when (val result = activityRepository.getActivities()) {
                is Result.Success -> {
                    emit(Result.Success(result.value))
                }
                else -> {
                    emit(Result.Failure)
                }
            }
        }.onStart {
            emit(Result.Loading)
        }.catch { throwable ->
            emit(Result.Error(throwable))
        }
    }
}
