package br.com.bravi.breaktheice.framework.remote.service

import br.com.bravi.breaktheice.data.remote.model.RemoteActivityModel
import br.com.bravi.breaktheice.util.constant.WEBSERVICE_ENDPOINT_ACTIVITY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @author Raphael Santos
 */
interface IActivityService {

    @GET(WEBSERVICE_ENDPOINT_ACTIVITY)
    suspend fun doActivity(): Response<RemoteActivityModel>

    @GET(WEBSERVICE_ENDPOINT_ACTIVITY)
    suspend fun doActivityFiltered(@QueryMap options: MutableMap<String, String>?): Response<RemoteActivityModel>
}
