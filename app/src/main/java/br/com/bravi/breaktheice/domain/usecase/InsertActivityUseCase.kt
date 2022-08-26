package br.com.bravi.breaktheice.domain.usecase

import br.com.bravi.breaktheice.commons.Result
import br.com.bravi.breaktheice.data.repository.ActivityRepository
import br.com.bravi.breaktheice.domain.entity.ActivityModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

/**
 * @author Raphael Santos
 */
class InsertActivityUseCase constructor(
    private val activityRepository: ActivityRepository
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
