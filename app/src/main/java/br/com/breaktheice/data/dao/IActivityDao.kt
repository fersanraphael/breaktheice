package br.com.breaktheice.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
@Dao
interface IActivityDao {

    @Query("SELECT * FROM activity WHERE _id = :id")
    suspend fun getActivity(id: Int): ActivityModel?

    @Query("SELECT * FROM activity")
    suspend fun getActivities(): MutableList<ActivityModel>?

    @Insert
    suspend fun insertActivity(activityModel: ActivityModel)

    @Delete
    suspend fun deleteActivity(activityModel: ActivityModel)
}
