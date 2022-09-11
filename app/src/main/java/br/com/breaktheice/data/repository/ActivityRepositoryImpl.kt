package br.com.breaktheice.data.repository

import br.com.breaktheice.commons.Result
import br.com.breaktheice.data.source.LocalActivityDataSource
import br.com.breaktheice.data.source.RemoteActivityDataSource
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
    private val localActivityDataSource: LocalActivityDataSource,
    private val remoteActivityDataSource: RemoteActivityDataSource,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IActivityRepository {

    override suspend fun callActivity(): Result<ActivityModel?> {
        return withContext(coroutineDispatcher) {
            val response: Response<ActivityModel> = remoteActivityDataSource.callActivity()
            if (response.isSuccessful) {
                val body: ActivityModel? = response.body()
                Result.Success(body)
            } else {
                Result.Failure
            }
        }
    }

    override suspend fun callActivityFiltered(options: MutableMap<String, String>): Result<ActivityModel?> {
        return withContext(coroutineDispatcher) {
            val response: Response<ActivityModel> = remoteActivityDataSource.callActivityFiltered(options)
            if (response.isSuccessful) {
                val body: ActivityModel? = response.body()
                Result.Success(body)
            } else {
                Result.Failure
            }
        }
    }

    override suspend fun deleteActivity(activityModel: ActivityModel) {
        withContext(coroutineDispatcher) {
            localActivityDataSource.deleteActivity(activityModel)
        }
    }

    override suspend fun getActivityById(id: Int): ActivityModel? {
        return withContext(coroutineDispatcher) {
            localActivityDataSource.getActivityById(id)
        }
    }

    override suspend fun getActivities(): MutableList<ActivityModel>? {
        return withContext(coroutineDispatcher) {
            localActivityDataSource.getActivities()
        }
    }

    override suspend fun insertActivity(activityModel: ActivityModel) {
        withContext(coroutineDispatcher) {
            localActivityDataSource.insertActivity(activityModel)
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
            localActivityDataSource.updateActivity(activityModel)
        }
    }
}
