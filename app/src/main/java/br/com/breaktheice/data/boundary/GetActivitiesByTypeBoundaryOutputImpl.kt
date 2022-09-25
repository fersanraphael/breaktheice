package br.com.breaktheice.data.boundary

import br.com.breaktheice.data.local.model.LocalActivityModel
import br.com.breaktheice.data.mapper.ActivityMapper
import br.com.breaktheice.data.repository.ActivityRepository
import br.com.breaktheice.domain.boundary.IGetActivitiesByTypeBoundaryOutput
import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
class GetActivitiesByTypeBoundaryOutputImpl constructor(
    private val activityMapper: ActivityMapper,
    private val activityRepository: ActivityRepository
) : IGetActivitiesByTypeBoundaryOutput {

    override suspend fun invoke(type: String): MutableList<ActivityModel>? {
        val localActivities: MutableList<LocalActivityModel> = activityRepository.getActivitiesByType(type) ?: return null
        return localActivities
            .map { localActivity -> activityMapper.localActivityToActivityMapper.map(localActivity) }
            .toMutableList()
    }
}
