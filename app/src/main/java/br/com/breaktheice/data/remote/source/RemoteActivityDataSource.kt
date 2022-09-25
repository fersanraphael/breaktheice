package br.com.breaktheice.data.remote.source

import br.com.breaktheice.data.remote.model.RemoteActivityModel
import br.com.breaktheice.data.remote.service.IActivityService
import retrofit2.Response
import retrofit2.Retrofit

/**
 * @author Raphael Santos
 */
class RemoteActivityDataSource constructor(
    private val retrofit: Retrofit
) {

    private val activityService: IActivityService by lazy {
        retrofit.create(IActivityService::class.java)
    }

    suspend fun callActivity(): Response<RemoteActivityModel> {
        return activityService.callActivity()
    }

    suspend fun callActivityFiltered(options: MutableMap<String, String>): Response<RemoteActivityModel> {
        return activityService.callActivityFiltered(options)
    }
}
