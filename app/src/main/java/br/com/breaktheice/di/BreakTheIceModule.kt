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
        LocalActivityDataSource(database = get())
    }
    factory {
        RemoteActivityDataSource(retrofit = get())
    }

    /*
     * Repository injection.
     */
    factory<IActivityRepository> {
        ActivityRepositoryImpl(
            localActivityDataSource = get(),
            remoteActivityDataSource = get()
        )
    }

    /*
     * Use Case injection.
     */
    factory {
        CallActivityFilteredUseCase(activityRepository = get())
    }
    factory {
        CallActivityUseCase(activityRepository = get())
    }
    factory {
        DeleteActivityUseCase(activityRepository = get())
    }
    factory {
        GetActivitiesByTypeUseCase(activityRepository = get())
    }
    factory {
        GetActivitiesUseCase(activityRepository = get())
    }
    factory {
        GetActivityByIdUseCase(activityRepository = get())
    }
    factory {
        InsertActivityUseCase(activityRepository = get())
    }
    factory {
        UpdateActivityFavoriteUseCase(activityRepository = get())
    }
    factory {
        UpdateActivityUseCase(activityRepository = get())
    }

    /*
     * View Model injection.
     */
    viewModel {
        MainViewModel(
            callActivityFilteredUseCase = get(),
            callActivityUseCase = get(),
            deleteActivityUseCase = get(),
            getActivitiesUseCase = get(),
            getActivityByIdUseCase = get(),
            getActivitiesByTypeUseCase = get(),
            insertActivityUseCase = get(),
            updateActivityFavoriteUseCase = get(),
            updateActivityUseCase = get()
        )
    }
}
