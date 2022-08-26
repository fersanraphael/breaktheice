package br.com.breaktheice.domain.repository

import br.com.breaktheice.domain.entity.ActivityModel
import retrofit2.Response

/**
 * @author Raphael Santos
 */
interface IActivityRepository {

    suspend fun doActivity(): Response<ActivityModel>

    suspend fun doActivityFiltered(options: MutableMap<String, String>): Response<ActivityModel>

    suspend fun getActivity(id: Int): ActivityModel?

    suspend fun getActivities(): MutableList<ActivityModel>?

    suspend fun insertActivity(activityModel: ActivityModel)

    suspend fun deleteActivity(activityModel: ActivityModel)
}
