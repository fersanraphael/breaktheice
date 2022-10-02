package br.com.breaktheice.data.repository

import br.com.breaktheice.data.source.LocalActivityDataSource
import br.com.breaktheice.data.mapper.ActivityMapper
import br.com.breaktheice.data.model.ActivityDTO
import br.com.breaktheice.data.source.RemoteActivityDataSource
import br.com.breaktheice.domain.model.ActivityModel
import br.com.breaktheice.domain.repository.IActivityRepository
import br.com.breaktheice.domain.utility.Result
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
            val response: Response<ActivityDTO> = remoteActivityDataSource.callActivity()
            if (response.isSuccessful) {
                val body: ActivityDTO = response.body() ?: return@withContext Result.Failure
                Result.Success(activityMapper.activityDTOToActivityModelMapper.map(body))
            } else {
                Result.Failure
            }
        }
    }

    override suspend fun callActivityFiltered(options: MutableMap<String, String>): Result<ActivityModel> {
        return withContext(coroutineDispatcher) {
            val response: Response<ActivityDTO> = remoteActivityDataSource.callActivityFiltered(options)
            if (response.isSuccessful) {
                val body: ActivityDTO = response.body() ?: return@withContext Result.Failure
                Result.Success(activityMapper.activityDTOToActivityModelMapper.map(body))
            } else {
                Result.Failure
            }
        }
    }

    override suspend fun deleteActivity(activityModel: ActivityModel) {
        withContext(coroutineDispatcher) {
            localActivityDataSource.deleteActivity(activityMapper.activityModelToActivityDTOMapper.map(activityModel))
        }
    }

    override suspend fun getActivities(): Result<MutableList<ActivityModel>> {
        return withContext(coroutineDispatcher) {
            val activities: MutableList<ActivityDTO> = localActivityDataSource.getActivities() ?: return@withContext Result.Failure
            Result.Success(
                activities
                    .map { localActivity -> activityMapper.activityDTOToActivityModelMapper.map(localActivity) }
                    .toMutableList()
            )
        }
    }

    override suspend fun getActivitiesByType(type: String): Result<MutableList<ActivityModel>> {
        return withContext(coroutineDispatcher) {
            val activities: MutableList<ActivityDTO> = localActivityDataSource.getActivitiesByType(type) ?: return@withContext Result.Failure
            Result.Success(
                activities
                    .map { localActivity -> activityMapper.activityDTOToActivityModelMapper.map(localActivity) }
                    .toMutableList()
            )
        }
    }

    override suspend fun getActivityById(id: Int): Result<ActivityModel> {
        return withContext(coroutineDispatcher) {
            val activityDTO: ActivityDTO = localActivityDataSource.getActivityById(id) ?: return@withContext Result.Failure
            Result.Success(activityMapper.activityDTOToActivityModelMapper.map(activityDTO))
        }
    }

    override suspend fun insertActivity(activityModel: ActivityModel) {
        withContext(coroutineDispatcher) {
            localActivityDataSource.insertActivity(activityMapper.activityModelToActivityDTOMapper.map(activityModel))
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
            localActivityDataSource.updateActivity(activityMapper.activityModelToActivityDTOMapper.map(activityModel))
        }
    }
}
