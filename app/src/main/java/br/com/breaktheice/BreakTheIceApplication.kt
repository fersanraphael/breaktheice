package br.com.breaktheice

import android.app.Application
import br.com.breaktheice.di.breakTheIceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @author Raphael Santos
 */
class BreakTheIceApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(applicationContext)

            modules(breakTheIceModule)
        }
    }
}
