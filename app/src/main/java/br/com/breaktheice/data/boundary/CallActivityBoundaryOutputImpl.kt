package br.com.breaktheice.data.boundary

import br.com.breaktheice.data.mapper.ActivityMapper
import br.com.breaktheice.data.remote.model.RemoteActivityModel
import br.com.breaktheice.data.repository.ActivityRepository
import br.com.breaktheice.domain.boundary.ICallActivityBoundaryOutput
import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
class CallActivityBoundaryOutputImpl constructor(
    private val activityMapper: ActivityMapper,
    private val activityRepository: ActivityRepository
) : ICallActivityBoundaryOutput {

    override suspend fun invoke(): ActivityModel? {
        val remoteActivityModel: RemoteActivityModel = activityRepository.callActivity() ?: return null
        return activityMapper.remoteActivityToActivityMapper.map(remoteActivityModel)
    }
}
