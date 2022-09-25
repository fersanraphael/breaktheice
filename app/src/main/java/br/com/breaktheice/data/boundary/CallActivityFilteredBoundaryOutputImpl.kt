package br.com.breaktheice.data.boundary

import br.com.breaktheice.data.mapper.ActivityMapper
import br.com.breaktheice.data.remote.model.RemoteActivityModel
import br.com.breaktheice.data.repository.ActivityRepository
import br.com.breaktheice.domain.boundary.ICallActivityFilteredBoundaryOutput
import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
class CallActivityFilteredBoundaryOutputImpl constructor(
    private val activityMapper: ActivityMapper,
    private val activityRepository: ActivityRepository
) : ICallActivityFilteredBoundaryOutput {

    override suspend fun invoke(options: MutableMap<String, String>): ActivityModel? {
        val remoteActivityModel: RemoteActivityModel = activityRepository.callActivityFiltered(options) ?: return null
        return activityMapper.remoteActivityToActivityMapper.map(remoteActivityModel)
    }
}
