package br.com.breaktheice.data.boundary

import br.com.breaktheice.data.local.model.LocalActivityModel
import br.com.breaktheice.data.mapper.ActivityMapper
import br.com.breaktheice.data.repository.ActivityRepository
import br.com.breaktheice.domain.boundary.IGetActivitiesBoundaryOutput
import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
class GetActivitiesBoundaryOutputImpl constructor(
    private val activityMapper: ActivityMapper,
    private val activityRepository: ActivityRepository
) : IGetActivitiesBoundaryOutput {

    override suspend fun invoke(): MutableList<ActivityModel>? {
        val localActivities: MutableList<LocalActivityModel> = activityRepository.getActivities() ?: return null
        return localActivities
            .map { localActivity -> activityMapper.localActivityToActivityMapper.map(localActivity) }
            .toMutableList()
    }
}
