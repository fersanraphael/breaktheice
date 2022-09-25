package br.com.breaktheice.domain.usecase

import br.com.breaktheice.domain.boundary.IGetActivityByIdBoundaryOutput
import br.com.breaktheice.domain.entity.ActivityModel
import br.com.breaktheice.domain.utility.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

/**
 * @author Raphael Santos
 */
class GetActivityByIdUseCase constructor(
    private val getActivityByIdBoundaryOutput: IGetActivityByIdBoundaryOutput
) {

    operator fun invoke(
        id: Int
    ): Flow<Result<ActivityModel>> {
        return flow {
            val activityModel: ActivityModel = getActivityByIdBoundaryOutput(id) ?: return@flow emit(Result.Failure)
            when (activityModel.isObjectValid) {
                true -> {
                    emit(Result.Success(activityModel))
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
