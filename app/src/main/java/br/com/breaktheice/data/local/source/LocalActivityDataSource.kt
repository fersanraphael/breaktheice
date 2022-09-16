package br.com.breaktheice.data.local.source

import br.com.breaktheice.data.local.BreakTheIceDatabase
import br.com.breaktheice.data.local.model.LocalActivityModel

/**
 * @author Raphael Santos
 */
class LocalActivityDataSource constructor(
    private val database: BreakTheIceDatabase
) {

    suspend fun deleteActivity(localActivityModel: LocalActivityModel) {
        database.activityDao()
            .deleteActivity(localActivityModel)
    }

    suspend fun getActivities(): MutableList<LocalActivityModel>? {
        return database.activityDao()
            .getActivities()
    }

    suspend fun getActivitiesByType(type: String): MutableList<LocalActivityModel>? {
        return database.activityDao()
            .getActivitiesByType(type)
    }

    suspend fun getActivityById(id: Int): LocalActivityModel? {
        return database.activityDao()
            .getActivityById(id)
    }

    suspend fun insertActivity(localActivityModel: LocalActivityModel) {
        database.activityDao()
            .insertActivity(localActivityModel)
    }

    suspend fun updateActivity(localActivityModel: LocalActivityModel) {
        database.activityDao()
            .updateActivity(localActivityModel)
    }

    suspend fun updateActivityFavorite(
        id: Int,
        favorite: Boolean
    ) {
        database.activityDao()
            .updateActivityFavorite(id, favorite)
    }
}
