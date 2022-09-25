package br.com.breaktheice.data.boundary

import br.com.breaktheice.data.repository.ActivityRepository
import br.com.breaktheice.domain.boundary.IUpdateActivityFavoriteBoundaryOutput

/**
 * @author Raphael Santos
 */
class UpdateActivityFavoriteBoundaryOutputImpl constructor(
    private val activityRepository: ActivityRepository
) : IUpdateActivityFavoriteBoundaryOutput {

    override suspend fun invoke(
        id: Int,
        favorite: Boolean
    ) {
        return activityRepository.updateActivityFavorite(id, favorite)
    }
}
