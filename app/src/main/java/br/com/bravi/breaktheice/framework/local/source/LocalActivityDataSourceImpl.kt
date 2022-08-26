package br.com.bravi.breaktheice.framework.local.source

import br.com.bravi.breaktheice.data.local.model.LocalActivityModel
import br.com.bravi.breaktheice.data.local.source.ILocalActivityDataSource
import br.com.bravi.breaktheice.framework.local.BreakTheIceDatabase

/**
 * @author Raphael Santos
 */
class LocalActivityDataSourceImpl constructor(
    private val database: BreakTheIceDatabase
) : ILocalActivityDataSource {

    override suspend fun getActivity(id: Int): LocalActivityModel? {
        return database.activityDao()
            .getActivity(id)
    }

    override suspend fun getActivities(): MutableList<LocalActivityModel>? {
        return database.activityDao()
            .getActivities()
    }

    override suspend fun insertActivity(activityModel: LocalActivityModel) {
        database.activityDao()
            .insertActivity(activityModel)
    }

    override suspend fun deleteActivity(activityModel: LocalActivityModel) {
        database.activityDao()
            .deleteActivity(activityModel)
    }
}
