package br.com.bravi.breaktheice.data.source.remote

import br.com.bravi.breaktheice.data.service.IActivityService
import br.com.bravi.breaktheice.domain.entity.ActivityModel
import retrofit2.Response
import retrofit2.Retrofit

/**
 * @author Raphael Santos
 */
class RemoteActivityDataSource constructor(
    private val retrofit: Retrofit
) {

    suspend fun doActivity(): Response<ActivityModel> {
        return retrofit.create(IActivityService::class.java)
            .doActivity()
    }

    suspend fun doActivityFiltered(options: MutableMap<String, String>?): Response<ActivityModel> {
        return retrofit.create(IActivityService::class.java)
            .doActivityFiltered(options)
    }
}
