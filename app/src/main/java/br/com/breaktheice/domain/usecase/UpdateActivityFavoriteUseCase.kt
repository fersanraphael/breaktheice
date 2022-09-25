package br.com.breaktheice.domain.usecase

import br.com.breaktheice.domain.boundary.IUpdateActivityFavoriteBoundaryOutput
import br.com.breaktheice.domain.utility.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

/**
 * @author Raphael Santos
 */
class UpdateActivityFavoriteUseCase constructor(
    private val updateActivityFavoriteBoundaryOutput: IUpdateActivityFavoriteBoundaryOutput
) {

    operator fun invoke(
        id: Int,
        favorite: Boolean
    ): Flow<Result<Unit>> {
        return flow {
            if (id != 0) {
                emit(Result.Success(updateActivityFavoriteBoundaryOutput(id, favorite)))
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
