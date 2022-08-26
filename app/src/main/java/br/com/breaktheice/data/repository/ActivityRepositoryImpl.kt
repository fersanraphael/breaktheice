package br.com.breaktheice.data.repository

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

    override suspend fun doActivity(): Response<ActivityModel> {
        return withContext(coroutineDispatcher) {
            remoteActivityDataSource.doActivity()
        }
    }

    override suspend fun doActivityFiltered(options: MutableMap<String, String>): Response<ActivityModel> {
        return withContext(coroutineDispatcher) {
            remoteActivityDataSource.doActivityFiltered(options)
        }
    }

    override suspend fun getActivity(id: Int): ActivityModel? {
        return withContext(coroutineDispatcher) {
            localActivityDataSource.getActivity(id)
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

    override suspend fun deleteActivity(activityModel: ActivityModel) {
        withContext(coroutineDispatcher) {
            localActivityDataSource.deleteActivity(activityModel)
        }
    }
}
