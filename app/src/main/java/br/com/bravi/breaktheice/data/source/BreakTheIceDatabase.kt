package br.com.bravi.breaktheice.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.bravi.breaktheice.data.dao.IActivityDao
import br.com.bravi.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
@Database(entities = [ActivityModel::class], version = 1, exportSchema = false)
abstract class BreakTheIceDatabase : RoomDatabase() {

    abstract fun activityDao(): IActivityDao
}
