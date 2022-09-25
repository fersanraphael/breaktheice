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

interface ICallActivityUseCase {

    operator fun invoke(): Flow<Result<ActivityModel>>
}

class CallActivityUseCaseImpl constructor(
    private val activityRepository: IActivityRepository
) : ICallActivityUseCase {

    override operator fun invoke(): Flow<Result<ActivityModel>> {
        return flow {
            when (val result = activityRepository.callActivity()) {
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
