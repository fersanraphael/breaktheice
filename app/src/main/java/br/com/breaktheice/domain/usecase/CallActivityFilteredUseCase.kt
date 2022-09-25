package br.com.breaktheice.domain.usecase

import br.com.breaktheice.domain.boundary.ICallActivityFilteredBoundaryOutput
import br.com.breaktheice.domain.entity.ActivityModel
import br.com.breaktheice.domain.utility.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

/**
 * @author Raphael Santos
 */
class CallActivityFilteredUseCase constructor(
    private val callActivityFilteredBoundaryOutput: ICallActivityFilteredBoundaryOutput
) {

    operator fun invoke(
        options: MutableMap<String, String>
    ): Flow<Result<ActivityModel>> {
        return flow {
            val activityModel: ActivityModel = callActivityFilteredBoundaryOutput(options) ?: return@flow emit(Result.Failure)
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
