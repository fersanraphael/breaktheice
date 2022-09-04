package br.com.breaktheice.domain.repository

import br.com.breaktheice.domain.entity.ActivityModel
import retrofit2.Response

/**
 * @author Raphael Santos
 */
interface IActivityRepository {

    suspend fun callActivity(): Response<ActivityModel>

    suspend fun callActivityFiltered(options: MutableMap<String, String>): Response<ActivityModel>

    suspend fun getActivityById(id: Int): ActivityModel?

    suspend fun getActivities(): MutableList<ActivityModel>?

    suspend fun insertActivity(activityModel: ActivityModel)

    suspend fun deleteActivity(activityModel: ActivityModel)
}
