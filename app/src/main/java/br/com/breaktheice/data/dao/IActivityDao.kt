package br.com.breaktheice.data.dao

import androidx.room.*
import br.com.breaktheice.data.model.LocalActivityModel

/**
 * @author Raphael Santos
 */
@Dao
interface IActivityDao {

    @Delete
    suspend fun deleteActivity(localActivityModel: LocalActivityModel)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertActivity(localActivityModel: LocalActivityModel)

    @Query("SELECT * FROM activity")
    suspend fun getActivities(): MutableList<LocalActivityModel>?

    @Query("SELECT * FROM activity WHERE type = :type")
    suspend fun getActivitiesByType(type: String): MutableList<LocalActivityModel>?

    @Query("SELECT * FROM activity WHERE _id = :id")
    suspend fun getActivityById(id: Int): LocalActivityModel?

    @Query("UPDATE activity SET favorite=:favorite WHERE _id=:id")
    suspend fun updateActivityFavorite(
        id: Int,
        favorite: Boolean
    )

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateActivity(localActivityModel: LocalActivityModel)
}
