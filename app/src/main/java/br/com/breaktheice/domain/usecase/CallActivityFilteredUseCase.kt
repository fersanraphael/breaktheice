package br.com.breaktheice.domain.usecase

import br.com.breaktheice.commons.Result
import br.com.breaktheice.domain.entity.ActivityModel
import br.com.breaktheice.domain.repository.IActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import retrofit2.Response

/**
 * @author Raphael Santos
 */
class CallActivityFilteredUseCase constructor(
    private val activityRepository: IActivityRepository
) {

    operator fun invoke(
        options: MutableMap<String, String>
    ): Flow<Result<ActivityModel>> {
        return flow {
            val response: Response<ActivityModel> = activityRepository.callActivityFiltered(options)
            if (response.isSuccessful) {
                val body: ActivityModel? = response.body()
                if (body?.isObjectValid == true) {
                    emit(Result.Success(body))
                } else {
                    emit(Result.Failure)
                }
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
