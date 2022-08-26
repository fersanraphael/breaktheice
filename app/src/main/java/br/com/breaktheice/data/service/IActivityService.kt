package br.com.breaktheice.data.service

import br.com.breaktheice.domain.entity.ActivityModel
import br.com.breaktheice.util.constant.WEBSERVICE_ENDPOINT_ACTIVITY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @author Raphael Santos
 */
interface IActivityService {

    @GET(WEBSERVICE_ENDPOINT_ACTIVITY)
    suspend fun doActivity(): Response<ActivityModel>

    @GET(WEBSERVICE_ENDPOINT_ACTIVITY)
    suspend fun doActivityFiltered(@QueryMap options: MutableMap<String, String>?): Response<ActivityModel>
}
