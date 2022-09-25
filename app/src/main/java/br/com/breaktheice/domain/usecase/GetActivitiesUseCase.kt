package br.com.breaktheice.domain.usecase

import br.com.breaktheice.domain.boundary.IGetActivitiesBoundaryOutput
import br.com.breaktheice.domain.entity.ActivityModel
import br.com.breaktheice.domain.utility.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

/**
 * @author Raphael Santos
 */
class GetActivitiesUseCase constructor(
    private val getActivitiesBoundaryOutput: IGetActivitiesBoundaryOutput
) {

    operator fun invoke(): Flow<Result<MutableList<ActivityModel>>> {
        return flow {
            val activities: MutableList<ActivityModel> = getActivitiesBoundaryOutput() ?: return@flow emit(Result.Failure)
            emit(Result.Success(activities))
        }.onStart {
            emit(Result.Loading)
        }.catch { throwable ->
            emit(Result.Error(throwable))
        }
    }
}
