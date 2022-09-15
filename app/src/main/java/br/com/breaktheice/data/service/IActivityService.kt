package br.com.breaktheice.data.service

import br.com.breaktheice.data.common.constant.WEBSERVICE_ENDPOINT_ACTIVITY
import br.com.breaktheice.data.model.RemoteActivityModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @author Raphael Santos
 */
interface IActivityService {

    @GET(WEBSERVICE_ENDPOINT_ACTIVITY)
    suspend fun callActivity(): Response<RemoteActivityModel>

    @GET(WEBSERVICE_ENDPOINT_ACTIVITY)
    suspend fun callActivityFiltered(@QueryMap options: MutableMap<String, String>?): Response<RemoteActivityModel>
}
