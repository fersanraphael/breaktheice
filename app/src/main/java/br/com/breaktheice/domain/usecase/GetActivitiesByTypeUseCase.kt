package br.com.breaktheice.domain.usecase

import br.com.breaktheice.domain.model.ActivityModel
import br.com.breaktheice.domain.repository.IActivityRepository
import br.com.breaktheice.domain.utility.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

/**
 * @author Raphael Santos
 */

interface IGetActivitiesByTypeUseCase {

    operator fun invoke(
        type: String
    ): Flow<Result<MutableList<ActivityModel>>>
}

class GetActivitiesByTypeUseCaseImpl constructor(
    private val activityRepository: IActivityRepository
) : IGetActivitiesByTypeUseCase {

    override operator fun invoke(
        type: String
    ): Flow<Result<MutableList<ActivityModel>>> {
        return flow {
            when (val result = activityRepository.getActivitiesByType(type)) {
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
