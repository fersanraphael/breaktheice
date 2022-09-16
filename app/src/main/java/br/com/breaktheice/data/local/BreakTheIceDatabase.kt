package br.com.breaktheice.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.breaktheice.data.local.dao.IActivityDao
import br.com.breaktheice.data.local.model.LocalActivityModel

/**
 * @author Raphael Santos
 */
@Database(entities = [LocalActivityModel::class], version = 1, exportSchema = false)
abstract class BreakTheIceDatabase : RoomDatabase() {

    abstract fun activityDao(): IActivityDao
}
