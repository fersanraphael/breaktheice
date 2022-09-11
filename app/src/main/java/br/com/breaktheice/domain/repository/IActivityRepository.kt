package br.com.breaktheice.domain.repository

import br.com.breaktheice.commons.Result
import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
interface IActivityRepository {

    suspend fun callActivity(): Result<ActivityModel?>

    suspend fun callActivityFiltered(options: MutableMap<String, String>): Result<ActivityModel?>

    suspend fun deleteActivity(activityModel: ActivityModel)

    suspend fun getActivityById(id: Int): ActivityModel?

    suspend fun getActivitiesByType(type: String): MutableList<ActivityModel>?

    suspend fun getActivities(): MutableList<ActivityModel>?

    suspend fun insertActivity(activityModel: ActivityModel)

    suspend fun updateActivityFavorite(
        id: Int,
        favorite: Boolean
    )

    suspend fun updateActivity(activityModel: ActivityModel)
}
