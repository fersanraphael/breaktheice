package br.com.breaktheice.data.source

import br.com.breaktheice.data.BreakTheIceDatabase
import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
class LocalActivityDataSource constructor(
    private val database: BreakTheIceDatabase
) {

    suspend fun getActivity(id: Int): ActivityModel? {
        return database.activityDao()
            .getActivity(id)
    }

    suspend fun getActivities(): MutableList<ActivityModel>? {
        return database.activityDao()
            .getActivities()
    }

    suspend fun insertActivity(activityModel: ActivityModel) {
        database.activityDao()
            .insertActivity(activityModel)
    }

    suspend fun deleteActivity(activityModel: ActivityModel) {
        database.activityDao()
            .deleteActivity(activityModel)
    }
}
