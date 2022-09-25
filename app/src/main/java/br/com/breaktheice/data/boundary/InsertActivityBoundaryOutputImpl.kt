package br.com.breaktheice.data.boundary

import br.com.breaktheice.data.mapper.ActivityMapper
import br.com.breaktheice.data.repository.ActivityRepository
import br.com.breaktheice.domain.boundary.IInsertActivityBoundaryOutput
import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
class InsertActivityBoundaryOutputImpl constructor(
    private val activityMapper: ActivityMapper,
    private val activityRepository: ActivityRepository
) : IInsertActivityBoundaryOutput {

    override suspend fun invoke(activityModel: ActivityModel) {
        return activityRepository.insertActivity(activityMapper.activityToLocalActivityMapper.map(activityModel))
    }
}
