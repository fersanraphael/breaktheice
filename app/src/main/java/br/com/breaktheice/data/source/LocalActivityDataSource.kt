package br.com.breaktheice.data.source

import br.com.breaktheice.data.BreakTheIceDatabase
import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
class LocalActivityDataSource constructor(
    private val database: BreakTheIceDatabase
) {

    suspend fun deleteActivity(activityModel: ActivityModel) {
        database.activityDao()
            .deleteActivity(activityModel)
    }

    suspend fun getActivities(): MutableList<ActivityModel>? {
        return database.activityDao()
            .getActivities()
    }

    suspend fun getActivitiesByType(type: String): MutableList<ActivityModel>? {
        return database.activityDao()
            .getActivitiesByType(type)
    }

    suspend fun getActivityById(id: Int): ActivityModel? {
        return database.activityDao()
            .getActivityById(id)
    }

    suspend fun insertActivity(activityModel: ActivityModel) {
        database.activityDao()
            .insertActivity(activityModel)
    }

    suspend fun updateActivity(activityModel: ActivityModel) {
        database.activityDao()
            .updateActivity(activityModel)
    }

    suspend fun updateActivityFavorite(
        id: Int,
        favorite: Boolean
    ) {
        database.activityDao()
            .updateActivityFavorite(id, favorite)
    }
}
