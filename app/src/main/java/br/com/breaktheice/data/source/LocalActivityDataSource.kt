package br.com.breaktheice.data.source

import br.com.breaktheice.data.BreakTheIceDatabase
import br.com.breaktheice.data.dao.IActivityDao
import br.com.breaktheice.data.model.ActivityDTO

/**
 * @author Raphael Santos
 */

interface IActivityDatabaseSource {

    suspend fun deleteActivity(activityDTO: ActivityDTO)

    suspend fun getActivities(): MutableList<ActivityDTO>?

    suspend fun getActivitiesByType(type: String): MutableList<ActivityDTO>?

    suspend fun getActivityById(id: Int): ActivityDTO?

    suspend fun insertActivity(activityDTO: ActivityDTO)

    suspend fun updateActivity(activityDTO: ActivityDTO)

    suspend fun updateActivityFavorite(
        id: Int,
        favorite: Boolean
    )
}

class LocalActivityDataSource constructor(
    private val database: BreakTheIceDatabase
) : IActivityDatabaseSource {

    private val activityDao: IActivityDao by lazy {
        database.activityDao()
    }

    override suspend fun deleteActivity(activityDTO: ActivityDTO) {
        activityDao.deleteActivity(activityDTO)
    }

    override suspend fun getActivities(): MutableList<ActivityDTO>? {
        return activityDao.getActivities()
    }

    override suspend fun getActivitiesByType(type: String): MutableList<ActivityDTO>? {
        return activityDao.getActivitiesByType(type)
    }

    override suspend fun getActivityById(id: Int): ActivityDTO? {
        return activityDao.getActivityById(id)
    }

    override suspend fun insertActivity(activityDTO: ActivityDTO) {
        activityDao.insertActivity(activityDTO)
    }

    override suspend fun updateActivity(activityDTO: ActivityDTO) {
        activityDao.updateActivity(activityDTO)
    }

    override suspend fun updateActivityFavorite(
        id: Int,
        favorite: Boolean
    ) {
        activityDao.updateActivityFavorite(id, favorite)
    }
}
