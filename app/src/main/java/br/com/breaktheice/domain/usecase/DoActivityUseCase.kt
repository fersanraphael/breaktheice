package br.com.breaktheice.domain.usecase

import br.com.breaktheice.commons.Result
import br.com.breaktheice.domain.entity.ActivityModel
import br.com.breaktheice.domain.entity.ErrorModel
import br.com.breaktheice.domain.repository.IActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

/**
 * @author Raphael Santos
 */
class DoActivityUseCase constructor(
    private val activityRepository: IActivityRepository
) {

    operator fun invoke(): Flow<Result<ActivityModel, ErrorModel?>> {
        return flow {
            when (val result = activityRepository.doActivity()) {
                is Result.Success -> {
                    if (result.value != null) {
                        emit(Result.Success(result.value))
                    } else {
                        emit(Result.Failure())
                    }
                }
                is Result.Failure -> {
                    emit(Result.Failure(result.value))
                }
                else -> {
                    emit(Result.Failure())
                }
            }
        }.onStart {
            emit(Result.Loading)
        }.catch { throwable ->
            emit(Result.Error(throwable))
        }
    }
}
