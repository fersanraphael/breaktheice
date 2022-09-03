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
class GetActivityUseCase constructor(
    private val activityRepository: IActivityRepository
) {

    operator fun invoke(
        id: Int
    ): Flow<Result<ActivityModel, ErrorModel>> {
        return flow {
            val activity: ActivityModel? = activityRepository.getActivity(id)
            if (activity != null) {
                emit(Result.Success(activity))
            } else {
                emit(Result.Failure())
            }
        }.onStart {
            emit(Result.Loading)
        }.catch { throwable ->
            emit(Result.Error(throwable))
        }
    }
}
