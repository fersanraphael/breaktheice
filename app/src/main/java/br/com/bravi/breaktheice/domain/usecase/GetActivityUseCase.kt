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
class GetActivityUseCase constructor(
    private val activityRepository: ActivityRepository
) {

    operator fun invoke(
        id: Int
    ): Flow<Result<ActivityModel>> {
        return flow {
            val activity: ActivityModel? = activityRepository.getActivity(id)
            if (activity != null) {
                emit(Result.Success(activity))
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
