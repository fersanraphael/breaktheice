package br.com.breaktheice.di

import androidx.room.Room
import br.com.breaktheice.commons.constant.DATABASE_NAME
import br.com.breaktheice.commons.constant.WEBSERVICE_BASEURL
import br.com.breaktheice.data.BreakTheIceDatabase
import br.com.breaktheice.data.repository.ActivityRepositoryImpl
import br.com.breaktheice.data.source.LocalActivityDataSource
import br.com.breaktheice.data.source.RemoteActivityDataSource
import br.com.breaktheice.domain.repository.IActivityRepository
import br.com.breaktheice.domain.usecase.*
import br.com.breaktheice.presentation.viewmodel.MainViewModel
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

val breakTheIceModule: Module = module {
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
     * Room Database injection.
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
        CallActivityFilteredUseCase(get())
    }
    factory {
        CallActivityUseCase(get())
    }
    factory {
        DeleteActivityUseCase(get())
    }
    factory {
        GetActivitiesUseCase(get())
    }
    factory {
        GetActivityByIdUseCase(get())
    }
    factory {
        GetActivitiesByTypeUseCase(get())
    }
    factory {
        InsertActivityUseCase(get())
    }
    factory {
        UpdateActivityFavoriteUseCase(get())
    }
    factory {
        UpdateActivityUseCase(get())
    }

    /*
     * View Model injection.
     */
    viewModel {
        MainViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get())
    }
}
