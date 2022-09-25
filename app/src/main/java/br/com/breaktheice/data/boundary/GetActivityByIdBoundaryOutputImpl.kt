package br.com.breaktheice.data.boundary

import br.com.breaktheice.data.local.model.LocalActivityModel
import br.com.breaktheice.data.mapper.ActivityMapper
import br.com.breaktheice.data.repository.ActivityRepository
import br.com.breaktheice.domain.boundary.IGetActivityByIdBoundaryOutput
import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
class GetActivityByIdBoundaryOutputImpl constructor(
    private val activityMapper: ActivityMapper,
    private val activityRepository: ActivityRepository
) : IGetActivityByIdBoundaryOutput {

    override suspend fun invoke(id: Int): ActivityModel? {
        val localActivity: LocalActivityModel = activityRepository.getActivityById(id) ?: return null
        return activityMapper.localActivityToActivityMapper.map(localActivity)
    }
}
