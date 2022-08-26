package br.com.bravi.breaktheice.data.local.source

import br.com.bravi.breaktheice.data.local.model.LocalActivityModel

/**
 * @author Raphael Santos
 */
interface ILocalActivityDataSource {

    suspend fun getActivity(id: Int): LocalActivityModel?

    suspend fun getActivities(): MutableList<LocalActivityModel>?

    suspend fun insertActivity(activityModel: LocalActivityModel)

    suspend fun deleteActivity(activityModel: LocalActivityModel)
}
