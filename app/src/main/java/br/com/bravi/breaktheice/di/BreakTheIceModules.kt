package br.com.bravi.breaktheice.di

import androidx.room.Room
import br.com.bravi.breaktheice.data.repository.ActivityRepositoryImpl
import br.com.bravi.breaktheice.data.BreakTheIceDatabase
import br.com.bravi.breaktheice.data.source.LocalActivityDataSource
import br.com.bravi.breaktheice.data.source.RemoteActivityDataSource
import br.com.bravi.breaktheice.domain.repository.IActivityRepository
import br.com.bravi.breaktheice.domain.usecase.*
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
        factory {
            LocalActivityDataSource(get())
        }
        factory {
            RemoteActivityDataSource(get())
        }

        /*
         * Repository injection.
         */
        factory<IActivityRepository> {
            ActivityRepositoryImpl(get(), get())
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
