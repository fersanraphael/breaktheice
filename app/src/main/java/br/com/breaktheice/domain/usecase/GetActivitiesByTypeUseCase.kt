package br.com.breaktheice.domain.usecase

import br.com.breaktheice.domain.boundary.IGetActivitiesByTypeBoundaryOutput
import br.com.breaktheice.domain.entity.ActivityModel
import br.com.breaktheice.domain.utility.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

/**
 * @author Raphael Santos
 */
class GetActivitiesByTypeUseCase constructor(
    private val getActivitiesByTypeBoundaryOutput: IGetActivitiesByTypeBoundaryOutput
) {

    operator fun invoke(
        type: String
    ): Flow<Result<MutableList<ActivityModel>>> {
        return flow {
            val activities: MutableList<ActivityModel> = getActivitiesByTypeBoundaryOutput(type) ?: return@flow emit(Result.Failure)
            emit(Result.Success(activities))
        }.onStart {
            emit(Result.Loading)
        }.catch { throwable ->
            emit(Result.Error(throwable))
        }
    }
}
