package br.com.breaktheice.domain.repository

import br.com.breaktheice.domain.entity.ActivityModel
import br.com.breaktheice.domain.utility.Result

/**
 * @author Raphael Santos
 */
interface IActivityRepository {

    suspend fun callActivity(): Result<ActivityModel>

    suspend fun callActivityFiltered(options: MutableMap<String, String>): Result<ActivityModel>

    suspend fun deleteActivity(activityModel: ActivityModel)

    suspend fun getActivities(): Result<MutableList<ActivityModel>>

    suspend fun getActivitiesByType(type: String): Result<MutableList<ActivityModel>>

    suspend fun getActivityById(id: Int): Result<ActivityModel>

    suspend fun insertActivity(activityModel: ActivityModel)

    suspend fun updateActivityFavorite(
        id: Int,
        favorite: Boolean
    )

    suspend fun updateActivity(activityModel: ActivityModel)
}
