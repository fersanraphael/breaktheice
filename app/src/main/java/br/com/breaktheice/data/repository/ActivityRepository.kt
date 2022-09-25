package br.com.breaktheice.data.repository

import br.com.breaktheice.data.local.model.LocalActivityModel
import br.com.breaktheice.data.local.source.LocalActivityDataSource
import br.com.breaktheice.data.remote.model.RemoteActivityModel
import br.com.breaktheice.data.remote.source.RemoteActivityDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * @author Raphael Santos
 */
class ActivityRepository constructor(
    private val localActivityDataSource: LocalActivityDataSource,
    private val remoteActivityDataSource: RemoteActivityDataSource,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun callActivity(): RemoteActivityModel? {
        return withContext(coroutineDispatcher) {
            val response: Response<RemoteActivityModel> = remoteActivityDataSource.callActivity()
            if (response.isSuccessful) {
                response.body() ?: return@withContext null
            } else {
                throw Exception()
            }
        }
    }

    suspend fun callActivityFiltered(options: MutableMap<String, String>): RemoteActivityModel? {
        return withContext(coroutineDispatcher) {
            val response: Response<RemoteActivityModel> = remoteActivityDataSource.callActivityFiltered(options)
            if (response.isSuccessful) {
                response.body() ?: return@withContext null
            } else {
                throw Exception()
            }
        }
    }

    suspend fun deleteActivity(localActivityModel: LocalActivityModel) {
        withContext(coroutineDispatcher) {
            localActivityDataSource.deleteActivity(localActivityModel)
        }
    }

    suspend fun getActivities(): MutableList<LocalActivityModel>? {
        return withContext(coroutineDispatcher) {
            localActivityDataSource.getActivities() ?: return@withContext null
        }
    }

    suspend fun getActivitiesByType(type: String): MutableList<LocalActivityModel>? {
        return withContext(coroutineDispatcher) {
            localActivityDataSource.getActivitiesByType(type) ?: return@withContext null
        }
    }

    suspend fun getActivityById(id: Int): LocalActivityModel? {
        return withContext(coroutineDispatcher) {
            localActivityDataSource.getActivityById(id) ?: return@withContext null
        }
    }

    suspend fun insertActivity(localActivityModel: LocalActivityModel) {
        withContext(coroutineDispatcher) {
            localActivityDataSource.insertActivity(localActivityModel)
        }
    }

    suspend fun updateActivityFavorite(
        id: Int,
        favorite: Boolean
    ) {
        withContext(coroutineDispatcher) {
            localActivityDataSource.updateActivityFavorite(id, favorite)
        }
    }

    suspend fun updateActivity(localActivityModel: LocalActivityModel) {
        withContext(coroutineDispatcher) {
            localActivityDataSource.updateActivity(localActivityModel)
        }
    }
}
