package br.com.breaktheice.data.local.source

import br.com.breaktheice.data.local.BreakTheIceDatabase
import br.com.breaktheice.data.local.dao.IActivityDao
import br.com.breaktheice.data.local.model.LocalActivityModel

/**
 * @author Raphael Santos
 */
class LocalActivityDataSource constructor(
    private val database: BreakTheIceDatabase
) {

    private val activityDao: IActivityDao by lazy {
        database.activityDao()
    }

    suspend fun deleteActivity(localActivityModel: LocalActivityModel) {
        activityDao.deleteActivity(localActivityModel)
    }

    suspend fun getActivities(): MutableList<LocalActivityModel>? {
        return activityDao.getActivities()
    }

    suspend fun getActivitiesByType(type: String): MutableList<LocalActivityModel>? {
        return activityDao.getActivitiesByType(type)
    }

    suspend fun getActivityById(id: Int): LocalActivityModel? {
        return activityDao.getActivityById(id)
    }

    suspend fun insertActivity(localActivityModel: LocalActivityModel) {
        activityDao.insertActivity(localActivityModel)
    }

    suspend fun updateActivity(localActivityModel: LocalActivityModel) {
        activityDao.updateActivity(localActivityModel)
    }

    suspend fun updateActivityFavorite(
        id: Int,
        favorite: Boolean
    ) {
        activityDao.updateActivityFavorite(id, favorite)
    }
}
