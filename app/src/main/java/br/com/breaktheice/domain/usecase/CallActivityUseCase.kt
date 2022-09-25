package br.com.breaktheice.domain.usecase

import br.com.breaktheice.domain.boundary.ICallActivityBoundaryOutput
import br.com.breaktheice.domain.entity.ActivityModel
import br.com.breaktheice.domain.utility.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

/**
 * @author Raphael Santos
 */
class CallActivityUseCase constructor(
    private val callActivityBoundaryOutput: ICallActivityBoundaryOutput
) {

    operator fun invoke(): Flow<Result<ActivityModel>> {
        return flow {
            val activityModel: ActivityModel = callActivityBoundaryOutput() ?: return@flow emit(Result.Failure)
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
