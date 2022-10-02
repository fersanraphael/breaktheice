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

interface ICallActivityFilteredUseCase {

    operator fun invoke(
        options: MutableMap<String, String>
    ): Flow<Result<ActivityModel>>
}

class CallActivityFilteredUseCaseImpl constructor(
    private val activityRepository: IActivityRepository
) : ICallActivityFilteredUseCase {

    override operator fun invoke(
        options: MutableMap<String, String>
    ): Flow<Result<ActivityModel>> {
        return flow {
            when (val result = activityRepository.callActivityFiltered(options)) {
                is Result.Success -> {
                    if (result.value.isObjectValid) {
                        emit(Result.Success(result.value))
                    } else {
                        emit(Result.Failure)
                    }
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
