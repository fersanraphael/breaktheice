package br.com.breaktheice.domain.usecase

import br.com.breaktheice.commons.Result
import br.com.breaktheice.domain.entity.ActivityModel
import br.com.breaktheice.domain.repository.IActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

/**
 * @author Raphael Santos
 */
class InsertActivityUseCase constructor(
    private val activityRepository: IActivityRepository
) {

    operator fun invoke(
        activityModel: ActivityModel?
    ): Flow<Result<Unit>> {
        return flow {
            if (activityModel != null) {
                emit(Result.Success(activityRepository.insertActivity(activityModel)))
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
