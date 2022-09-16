package br.com.breaktheice.data.mapper

import br.com.breaktheice.data.local.mapper.ActivityToLocalActivityMapper
import br.com.breaktheice.data.local.mapper.LocalActivityToActivityMapper
import br.com.breaktheice.data.remote.mapper.ActivityToRemoteActivityMapper
import br.com.breaktheice.data.remote.mapper.RemoteActivityToActivityMapper

/**
 * @author Raphael Santos
 */
data class ActivityMapper constructor(
    val activityToLocalActivityMapper: ActivityToLocalActivityMapper,
    val activityToRemoteActivityMapper: ActivityToRemoteActivityMapper,
    val localActivityToActivityMapper: LocalActivityToActivityMapper,
    val remoteActivityToActivityMapper: RemoteActivityToActivityMapper
)
