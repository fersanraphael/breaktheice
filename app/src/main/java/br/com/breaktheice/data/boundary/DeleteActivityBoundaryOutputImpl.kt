package br.com.breaktheice.data.boundary

import br.com.breaktheice.data.mapper.ActivityMapper
import br.com.breaktheice.data.repository.ActivityRepository
import br.com.breaktheice.domain.boundary.IDeleteActivityBoundaryOutput
import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
class DeleteActivityBoundaryOutputImpl constructor(
    private val activityMapper: ActivityMapper,
    private val activityRepository: ActivityRepository
) : IDeleteActivityBoundaryOutput {

    override suspend fun invoke(activityModel: ActivityModel) {
        return activityRepository.deleteActivity(activityMapper.activityToLocalActivityMapper.map(activityModel))
    }
}
