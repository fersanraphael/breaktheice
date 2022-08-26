package br.com.bravi.breaktheice.framework.remote.source

import br.com.bravi.breaktheice.data.remote.model.RemoteActivityModel
import br.com.bravi.breaktheice.data.remote.source.IRemoteActivityDataSource
import br.com.bravi.breaktheice.framework.remote.service.IActivityService
import retrofit2.Response
import retrofit2.Retrofit

/**
 * @author Raphael Santos
 */
class RemoteActivityDataSourceImpl constructor(
    private val retrofit: Retrofit
) : IRemoteActivityDataSource {

    override suspend fun doActivity(): Response<RemoteActivityModel> {
        return retrofit.create(IActivityService::class.java)
            .doActivity()
    }

    override suspend fun doActivityFiltered(options: MutableMap<String, String>?): Response<RemoteActivityModel> {
        return retrofit.create(IActivityService::class.java)
            .doActivityFiltered(options)
    }
}
