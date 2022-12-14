package br.com.breaktheice.domain.usecase

import br.com.breaktheice.domain.repository.IActivityRepository
import br.com.breaktheice.domain.utility.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

/**
 * @author Raphael Santos
 */

interface IUpdateActivityFavoriteUseCase {

    operator fun invoke(
        id: Int,
        favorite: Boolean
    ): Flow<Result<Unit>>
}

class UpdateActivityFavoriteUseCaseImpl constructor(
    private val activityRepository: IActivityRepository
) : IUpdateActivityFavoriteUseCase {

    override operator fun invoke(
        id: Int,
        favorite: Boolean
    ): Flow<Result<Unit>> {
        return flow {
            if (id != 0) {
                emit(Result.Success(activityRepository.updateActivityFavorite(id, favorite)))
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
