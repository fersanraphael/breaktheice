package br.com.breaktheice.data.boundary

import br.com.breaktheice.data.mapper.ActivityMapper
import br.com.breaktheice.data.repository.ActivityRepository
import br.com.breaktheice.domain.boundary.IUpdateActivityBoundaryOutput
import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
class UpdateActivityBoundaryOutputImpl constructor(
    private val activityMapper: ActivityMapper,
    private val activityRepository: ActivityRepository
) : IUpdateActivityBoundaryOutput {

    override suspend fun invoke(activityModel: ActivityModel) {
        return activityRepository.updateActivity(activityMapper.activityToLocalActivityMapper.map(activityModel))
    }
}
