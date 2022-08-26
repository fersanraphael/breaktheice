package br.com.bravi.breaktheice.data.remote.source

import br.com.bravi.breaktheice.data.remote.model.RemoteActivityModel
import retrofit2.Response

/**
 * @author Raphael Santos
 */
interface IRemoteActivityDataSource {

    suspend fun doActivity(): Response<RemoteActivityModel>

    suspend fun doActivityFiltered(options: MutableMap<String, String>?): Response<RemoteActivityModel>
}
