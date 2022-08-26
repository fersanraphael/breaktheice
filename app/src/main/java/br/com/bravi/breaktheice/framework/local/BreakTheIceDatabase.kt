package br.com.bravi.breaktheice.framework.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.bravi.breaktheice.domain.entity.ActivityModel
import br.com.bravi.breaktheice.framework.local.dao.IActivityDao

/**
 * @author Raphael Santos
 */
@Database(entities = [ActivityModel::class], version = 1, exportSchema = false)
abstract class BreakTheIceDatabase : RoomDatabase() {

    abstract fun activityDao(): IActivityDao
}
