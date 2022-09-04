package br.com.breaktheice.data.repository

import br.com.breaktheice.commons.Result
import br.com.breaktheice.commons.asError
import br.com.breaktheice.data.source.LocalActivityDataSource
import br.com.breaktheice.data.source.RemoteActivityDataSource
import br.com.breaktheice.domain.entity.ActivityModel
import br.com.breaktheice.domain.entity.ErrorModel
import br.com.breaktheice.domain.repository.IActivityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * @author Raphael Santos
 */
class ActivityRepositoryImpl constructor(
    private val localActivityDataSource: LocalActivityDataSource,
    private val remoteActivityDataSource: RemoteActivityDataSource,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IActivityRepository {

    override suspend fun doActivity(): Result<ActivityModel?, ErrorModel?> {
        return withContext(coroutineDispatcher) {
            val response: Response<ActivityModel> = remoteActivityDataSource.doActivity()
            if (response.isSuccessful) {
                val body: ActivityModel? = response.body()
                Result.Success(body)
            } else {
                val errorBody: ResponseBody? = response.errorBody()
                Result.Failure(errorBody?.asError())
            }
        }
    }

    override suspend fun doActivityFiltered(options: MutableMap<String, String>): Result<ActivityModel?, ErrorModel?> {
        return withContext(coroutineDispatcher) {
            val response: Response<ActivityModel> = remoteActivityDataSource.doActivityFiltered(options)
            if (response.isSuccessful) {
                val body: ActivityModel? = response.body()
                Result.Success(body)
            } else {
                val errorBody: ResponseBody? = response.errorBody()
                Result.Failure(errorBody?.asError())
            }
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
