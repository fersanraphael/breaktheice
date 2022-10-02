package br.com.breaktheice.data.source

import br.com.breaktheice.data.model.ActivityDTO
import br.com.breaktheice.data.service.IActivityService
import retrofit2.Response
import retrofit2.Retrofit

/**
 * @author Raphael Santos
 */

interface IActivityWeb {

    suspend fun callActivity(): Response<ActivityDTO>

    suspend fun callActivityFiltered(options: MutableMap<String, String>): Response<ActivityDTO>
}

class RemoteActivityDataSource constructor(
    private val retrofit: Retrofit
) : IActivityWeb {

    private val activityService: IActivityService by lazy {
        retrofit.create(IActivityService::class.java)
    }

    override suspend fun callActivity(): Response<ActivityDTO> {
        return activityService.callActivity()
    }

    override suspend fun callActivityFiltered(options: MutableMap<String, String>): Response<ActivityDTO> {
        return activityService.callActivityFiltered(options)
    }
}
