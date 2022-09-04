package br.com.breaktheice.domain.repository

import br.com.breaktheice.commons.Result
import br.com.breaktheice.domain.entity.ActivityModel
import br.com.breaktheice.domain.entity.ErrorModel

/**
 * @author Raphael Santos
 */
interface IActivityRepository {

    suspend fun doActivity(): Result<ActivityModel?, ErrorModel?>

    suspend fun doActivityFiltered(options: MutableMap<String, String>): Result<ActivityModel?, ErrorModel?>

    suspend fun getActivity(id: Int): ActivityModel?

    suspend fun getActivities(): MutableList<ActivityModel>?

    suspend fun insertActivity(activityModel: ActivityModel)

    suspend fun deleteActivity(activityModel: ActivityModel)
}
