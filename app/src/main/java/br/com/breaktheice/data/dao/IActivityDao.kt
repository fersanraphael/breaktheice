package br.com.breaktheice.data.dao

import androidx.room.*
import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
@Dao
interface IActivityDao {

    @Delete
    suspend fun deleteActivity(activityModel: ActivityModel)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertActivity(activityModel: ActivityModel)

    @Query("SELECT * FROM activity")
    suspend fun getActivities(): MutableList<ActivityModel>?

    @Query("SELECT * FROM activity WHERE type = :type")
    suspend fun getActivitiesByType(type: String): MutableList<ActivityModel>?

    @Query("SELECT * FROM activity WHERE _id = :id")
    suspend fun getActivityById(id: Int): ActivityModel?

    @Query("UPDATE activity SET favorite=:favorite WHERE _id=:id")
    suspend fun updateActivityFavorite(
        id: Int,
        favorite: Boolean
    )

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateActivity(activityModel: ActivityModel)
}
