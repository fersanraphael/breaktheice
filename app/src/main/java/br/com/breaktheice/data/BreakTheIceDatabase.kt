package br.com.breaktheice.data

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.breaktheice.data.dao.IActivityDao
import br.com.breaktheice.data.model.ActivityDTO

/**
 * @author Raphael Santos
 */
@Database(entities = [ActivityDTO::class], version = 1, exportSchema = false)
abstract class BreakTheIceDatabase : RoomDatabase() {

    abstract fun activityDao(): IActivityDao
}
