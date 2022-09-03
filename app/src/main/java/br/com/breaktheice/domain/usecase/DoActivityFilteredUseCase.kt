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
class DoActivityFilteredUseCase constructor(
    private val activityRepository: IActivityRepository
) {

    operator fun invoke(
        options: MutableMap<String, String>
    ): Flow<Result<ActivityModel, ErrorModel?>> {
        return flow {
            when (val result: Result<ActivityModel, ErrorModel?> = activityRepository.doActivityFiltered(options)) {
                is Result.Success -> {
                    emit(Result.Success(result.value))
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
