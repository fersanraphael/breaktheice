package br.com.bravi.breaktheice.framework.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.bravi.breaktheice.data.local.model.LocalActivityModel

/**
 * @author Raphael Santos
 */
@Dao
interface IActivityDao {

    @Query("SELECT * FROM activity WHERE _id = :id")
    suspend fun getActivity(id: Int): LocalActivityModel?

    @Query("SELECT * FROM activity")
    suspend fun getActivities(): MutableList<LocalActivityModel>?

    @Insert
    suspend fun insertActivity(activityModel: LocalActivityModel)

    @Delete
    suspend fun deleteActivity(activityModel: LocalActivityModel)
}
