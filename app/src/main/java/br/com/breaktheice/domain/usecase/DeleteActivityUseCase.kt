package br.com.breaktheice.domain.usecase

import br.com.breaktheice.domain.boundary.IDeleteActivityBoundaryOutput
import br.com.breaktheice.domain.entity.ActivityModel
import br.com.breaktheice.domain.utility.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

/**
 * @author Raphael Santos
 */
class DeleteActivityUseCase constructor(
    private val deleteActivityBoundaryOutput: IDeleteActivityBoundaryOutput
) {

    operator fun invoke(
        activityModel: ActivityModel?
    ): Flow<Result<Unit>> {
        return flow {
            if (activityModel != null) {
                emit(Result.Success(deleteActivityBoundaryOutput(activityModel)))
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
