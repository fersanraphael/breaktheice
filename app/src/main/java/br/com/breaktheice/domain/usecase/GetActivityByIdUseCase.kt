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

interface IGetActivityByIdUseCase {

    operator fun invoke(
        id: Int
    ): Flow<Result<ActivityModel>>
}

class GetActivityByIdUseCaseImpl constructor(
    private val activityRepository: IActivityRepository
) : IGetActivityByIdUseCase {

    override operator fun invoke(
        id: Int
    ): Flow<Result<ActivityModel>> {
        return flow {
            when (val result = activityRepository.getActivityById(id)) {
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
