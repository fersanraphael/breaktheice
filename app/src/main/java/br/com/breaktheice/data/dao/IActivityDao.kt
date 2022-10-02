package br.com.breaktheice.data.dao

import androidx.room.*
import br.com.breaktheice.data.model.ActivityDTO

/**
 * @author Raphael Santos
 */
@Dao
interface IActivityDao {

    @Delete
    suspend fun deleteActivity(activityDTO: ActivityDTO)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertActivity(activityDTO: ActivityDTO)

    @Query("SELECT * FROM activity")
    suspend fun getActivities(): MutableList<ActivityDTO>?

    @Query("SELECT * FROM activity WHERE type = :type")
    suspend fun getActivitiesByType(type: String): MutableList<ActivityDTO>?

    @Query("SELECT * FROM activity WHERE _id = :id")
    suspend fun getActivityById(id: Int): ActivityDTO?

    @Query("UPDATE activity SET favorite=:favorite WHERE _id=:id")
    suspend fun updateActivityFavorite(
        id: Int,
        favorite: Boolean
    )

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateActivity(activityDTO: ActivityDTO)
}
