package br.com.bravi.breaktheice.di

import androidx.room.Room
import br.com.bravi.breaktheice.data.local.source.ILocalActivityDataSource
import br.com.bravi.breaktheice.data.remote.source.IRemoteActivityDataSource
import br.com.bravi.breaktheice.data.repository.ActivityRepository
import br.com.bravi.breaktheice.domain.usecase.*
import br.com.bravi.breaktheice.framework.local.BreakTheIceDatabase
import br.com.bravi.breaktheice.framework.local.source.LocalActivityDataSourceImpl
import br.com.bravi.breaktheice.framework.remote.source.RemoteActivityDataSourceImpl
import br.com.bravi.breaktheice.presentation.viewmodel.MainViewModel
import br.com.bravi.breaktheice.util.constant.DATABASE_NAME
import br.com.bravi.breaktheice.util.constant.WEBSERVICE_BASEURL
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Raphael Santos
 */

fun injectBreakTheIceModule(): Module {
    return module {
        /*
         * OkHttpClient injection.
         */
        factory {
            OkHttpClient.Builder()
                .build()
        }

        /*
         * Retrofit injection.
         */
        single {
            Retrofit.Builder()
                .baseUrl(WEBSERVICE_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
        }

        /*
         * Room Database Injection.
         */
        single {
            Room.databaseBuilder(androidContext(), BreakTheIceDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
        }

        /*
         * Data Source injection.
         */
        factory<ILocalActivityDataSource> {
            LocalActivityDataSourceImpl(get())
        }
        factory<IRemoteActivityDataSource> {
            RemoteActivityDataSourceImpl(get())
        }

        /*
         * Repository injection.
         */
        factory {
            ActivityRepository(get(), get())
        }

        /*
         * Use Case injection.
         */
        factory {
            DeleteActivityUseCase(get())
        }
        factory {
            DoActivityFilteredUseCase(get())
        }
        factory {
            DoActivityUseCase(get())
        }
        factory {
            GetActivitiesUseCase(get())
        }
        factory {
            GetActivityUseCase(get())
        }
        factory {
            InsertActivityUseCase(get())
        }

        /*
         * View Model injection.
         */
        viewModel {
            MainViewModel(get(), get(), get(), get(), get(), get())
        }
    }
}
