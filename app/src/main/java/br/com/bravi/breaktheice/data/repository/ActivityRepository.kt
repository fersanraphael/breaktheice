package br.com.bravi.breaktheice.data.repository

import br.com.bravi.breaktheice.data.local.model.LocalActivityModel
import br.com.bravi.breaktheice.data.local.source.ILocalActivityDataSource
import br.com.bravi.breaktheice.data.remote.model.RemoteActivityModel
import br.com.bravi.breaktheice.data.remote.source.IRemoteActivityDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * @author Raphael Santos
 */
class ActivityRepository constructor(
    private val localActivityDataSource: ILocalActivityDataSource,
    private val remoteActivityDataSource: IRemoteActivityDataSource,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun doActivity(): Response<RemoteActivityModel> {
        return withContext(coroutineDispatcher) {
            remoteActivityDataSource.doActivity()
        }
    }

    suspend fun doActivityFiltered(options: MutableMap<String, String>): Response<RemoteActivityModel> {
        return withContext(coroutineDispatcher) {
            remoteActivityDataSource.doActivityFiltered(options)
        }
    }

    suspend fun getActivity(id: Int): LocalActivityModel? {
        return withContext(coroutineDispatcher) {
            localActivityDataSource.getActivity(id)
        }
    }

    suspend fun getActivities(): MutableList<LocalActivityModel>? {
        return withContext(coroutineDispatcher) {
            localActivityDataSource.getActivities()
        }
    }

    suspend fun insertActivity(activityModel: LocalActivityModel) {
        withContext(coroutineDispatcher) {
            localActivityDataSource.insertActivity(activityModel)
        }
    }

    suspend fun deleteActivity(activityModel: LocalActivityModel) {
        withContext(coroutineDispatcher) {
            localActivityDataSource.deleteActivity(activityModel)
        }
    }
}
