package br.com.breaktheice.data.repository

import br.com.breaktheice.data.local.model.LocalActivityModel
import br.com.breaktheice.data.local.source.LocalActivityDataSource
import br.com.breaktheice.data.mapper.ActivityMapper
import br.com.breaktheice.data.mapper.ListMapper
import br.com.breaktheice.data.remote.model.RemoteActivityModel
import br.com.breaktheice.data.remote.source.RemoteActivityDataSource
import br.com.breaktheice.domain.common.Result
import br.com.breaktheice.domain.entity.ActivityModel
import br.com.breaktheice.domain.repository.IActivityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * @author Raphael Santos
 */
class ActivityRepositoryImpl constructor(
    private val activityMapper: ActivityMapper,
    private val localActivityDataSource: LocalActivityDataSource,
    private val remoteActivityDataSource: RemoteActivityDataSource,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IActivityRepository {

    override suspend fun callActivity(): Result<ActivityModel> {
        return withContext(coroutineDispatcher) {
            val response: Response<RemoteActivityModel> = remoteActivityDataSource.callActivity()
            if (response.isSuccessful) {
                val body: RemoteActivityModel = response.body() ?: return@withContext Result.Failure
                Result.Success(activityMapper.remoteActivityToActivityMapper.map(body))
            } else {
                Result.Failure
            }
        }
    }

    override suspend fun callActivityFiltered(options: MutableMap<String, String>): Result<ActivityModel> {
        return withContext(coroutineDispatcher) {
            val response: Response<RemoteActivityModel> = remoteActivityDataSource.callActivityFiltered(options)
            if (response.isSuccessful) {
                val body: RemoteActivityModel = response.body() ?: return@withContext Result.Failure
                Result.Success(activityMapper.remoteActivityToActivityMapper.map(body))
            } else {
                Result.Failure
            }
        }
    }

    override suspend fun deleteActivity(activityModel: ActivityModel) {
        withContext(coroutineDispatcher) {
            localActivityDataSource.deleteActivity(activityMapper.activityToLocalActivityMapper.map(activityModel))
        }
    }

    override suspend fun getActivities(): Result<MutableList<ActivityModel>> {
        return withContext(coroutineDispatcher) {
            val localActivities: MutableList<LocalActivityModel> = localActivityDataSource.getActivities() ?: return@withContext Result.Failure
            Result.Success(ArrayList(ListMapper(activityMapper.localActivityToActivityMapper).map(localActivities)))
        }
    }

    override suspend fun getActivitiesByType(type: String): Result<MutableList<ActivityModel>> {
        return withContext(coroutineDispatcher) {
            val localActivities: MutableList<LocalActivityModel> = localActivityDataSource.getActivitiesByType(type) ?: return@withContext Result.Failure
            Result.Success(ArrayList(ListMapper(activityMapper.localActivityToActivityMapper).map(localActivities)))
        }
    }

    override suspend fun getActivityById(id: Int): Result<ActivityModel> {
        return withContext(coroutineDispatcher) {
            val localActivityModel: LocalActivityModel = localActivityDataSource.getActivityById(id) ?: return@withContext Result.Failure
            Result.Success(activityMapper.localActivityToActivityMapper.map(localActivityModel))
        }
    }

    override suspend fun insertActivity(activityModel: ActivityModel) {
        withContext(coroutineDispatcher) {
            localActivityDataSource.insertActivity(activityMapper.activityToLocalActivityMapper.map(activityModel))
        }
    }

    override suspend fun updateActivityFavorite(
        id: Int,
        favorite: Boolean
    ) {
        withContext(coroutineDispatcher) {
            localActivityDataSource.updateActivityFavorite(id, favorite)
        }
    }

    override suspend fun updateActivity(activityModel: ActivityModel) {
        withContext(coroutineDispatcher) {
            localActivityDataSource.updateActivity(activityMapper.activityToLocalActivityMapper.map(activityModel))
        }
    }
}
