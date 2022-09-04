package br.com.breaktheice.data.source

import br.com.breaktheice.data.service.IActivityService
import br.com.breaktheice.domain.entity.ActivityModel
import retrofit2.Response
import retrofit2.Retrofit

/**
 * @author Raphael Santos
 */
class RemoteActivityDataSource constructor(
    private val retrofit: Retrofit
) {

    suspend fun callActivity(): Response<ActivityModel> {
        return retrofit.create(IActivityService::class.java)
            .callActivity()
    }

    suspend fun callActivityFiltered(options: MutableMap<String, String>): Response<ActivityModel> {
        return retrofit.create(IActivityService::class.java)
            .callActivityFiltered(options)
    }
}
