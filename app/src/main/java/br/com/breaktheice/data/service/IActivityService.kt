package br.com.breaktheice.data.service

import br.com.breaktheice.data.model.ActivityDTO
import br.com.breaktheice.data.utility.WEBSERVICE_ENDPOINT_ACTIVITY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @author Raphael Santos
 */
interface IActivityService {

    @GET(WEBSERVICE_ENDPOINT_ACTIVITY)
    suspend fun callActivity(): Response<ActivityDTO>

    @GET(WEBSERVICE_ENDPOINT_ACTIVITY)
    suspend fun callActivityFiltered(@QueryMap options: MutableMap<String, String>?): Response<ActivityDTO>
}
