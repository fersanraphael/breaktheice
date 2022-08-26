package br.com.bravi.breaktheice.domain.usecase

import br.com.bravi.breaktheice.commons.Result
import br.com.bravi.breaktheice.domain.entity.ActivityModel
import br.com.bravi.breaktheice.domain.repository.IActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import retrofit2.Response

/**
 * @author Raphael Santos
 */
class DoActivityUseCase constructor(
    private val activityRepository: IActivityRepository
) {

    operator fun invoke(): Flow<Result<ActivityModel>> {
        return flow {
            val response: Response<ActivityModel> = activityRepository.doActivity()
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
