package br.com.breaktheice.di

import androidx.room.Room
import br.com.breaktheice.data.boundary.*
import br.com.breaktheice.data.local.BreakTheIceDatabase
import br.com.breaktheice.data.local.mapper.ActivityToLocalActivityMapper
import br.com.breaktheice.data.local.mapper.LocalActivityToActivityMapper
import br.com.breaktheice.data.local.source.LocalActivityDataSource
import br.com.breaktheice.data.mapper.ActivityMapper
import br.com.breaktheice.data.remote.mapper.ActivityToRemoteActivityMapper
import br.com.breaktheice.data.remote.mapper.RemoteActivityToActivityMapper
import br.com.breaktheice.data.remote.source.RemoteActivityDataSource
import br.com.breaktheice.data.repository.ActivityRepository
import br.com.breaktheice.data.utility.DATABASE_NAME
import br.com.breaktheice.data.utility.WEBSERVICE_BASEURL
import br.com.breaktheice.domain.boundary.*
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
     * Activity Mapper injection.
     */
    single {
        ActivityMapper(
            activityToLocalActivityMapper = ActivityToLocalActivityMapper(),
            activityToRemoteActivityMapper = ActivityToRemoteActivityMapper(),
            localActivityToActivityMapper = LocalActivityToActivityMapper(),
            remoteActivityToActivityMapper = RemoteActivityToActivityMapper()
        )
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
    factory {
        ActivityRepository(
            localActivityDataSource = get(),
            remoteActivityDataSource = get()
        )
    }

    /*
     * Boundary injection.
     */
    factory<ICallActivityBoundaryOutput> {
        CallActivityBoundaryOutputImpl(
            activityMapper = get(),
            activityRepository = get()
        )
    }
    factory<ICallActivityFilteredBoundaryOutput> {
        CallActivityFilteredBoundaryOutputImpl(
            activityMapper = get(),
            activityRepository = get()
        )
    }
    factory<IDeleteActivityBoundaryOutput> {
        DeleteActivityBoundaryOutputImpl(
            activityMapper = get(),
            activityRepository = get()
        )
    }
    factory<IGetActivitiesBoundaryOutput> {
        GetActivitiesBoundaryOutputImpl(
            activityMapper = get(),
            activityRepository = get()
        )
    }
    factory<IGetActivitiesByTypeBoundaryOutput> {
        GetActivitiesByTypeBoundaryOutputImpl(
            activityMapper = get(),
            activityRepository = get()
        )
    }
    factory<IGetActivityByIdBoundaryOutput> {
        GetActivityByIdBoundaryOutputImpl(
            activityMapper = get(),
            activityRepository = get()
        )
    }
    factory<IInsertActivityBoundaryOutput> {
        InsertActivityBoundaryOutputImpl(
            activityMapper = get(),
            activityRepository = get()
        )
    }
    factory<IUpdateActivityBoundaryOutput> {
        UpdateActivityBoundaryOutputImpl(
            activityMapper = get(),
            activityRepository = get()
        )
    }
    factory<IUpdateActivityFavoriteBoundaryOutput> {
        UpdateActivityFavoriteBoundaryOutputImpl(
            activityRepository = get()
        )
    }

    /*
     * Use Case injection.
     */
    factory {
        CallActivityFilteredUseCase(callActivityFilteredBoundaryOutput = get())
    }
    factory {
        CallActivityUseCase(callActivityBoundaryOutput = get())
    }
    factory {
        DeleteActivityUseCase(deleteActivityBoundaryOutput = get())
    }
    factory {
        GetActivitiesByTypeUseCase(getActivitiesByTypeBoundaryOutput = get())
    }
    factory {
        GetActivitiesUseCase(getActivitiesBoundaryOutput = get())
    }
    factory {
        GetActivityByIdUseCase(getActivityByIdBoundaryOutput = get())
    }
    factory {
        InsertActivityUseCase(insertActivityBoundaryOutput = get())
    }
    factory {
        UpdateActivityFavoriteUseCase(updateActivityFavoriteBoundaryOutput = get())
    }
    factory {
        UpdateActivityUseCase(updateActivityBoundaryOutput = get())
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
