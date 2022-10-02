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

interface IUpdateActivityUseCase {

    operator fun invoke(
        activityModel: ActivityModel?
    ): Flow<Result<Unit>>
}

class UpdateActivityUseCaseImpl constructor(
    private val activityRepository: IActivityRepository
) : IUpdateActivityUseCase {

    override operator fun invoke(
        activityModel: ActivityModel?
    ): Flow<Result<Unit>> {
        return flow {
            if (activityModel != null) {
                emit(Result.Success(activityRepository.updateActivity(activityModel)))
            } else {
                emit(Result.Failure)
            }
        }.onStart {
            emit(Result.Loading)
        }.catch { throwable ->
            emit(Result.Error(throwable))
        }
    }
}
