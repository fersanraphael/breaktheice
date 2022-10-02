package br.com.breaktheice.di

import androidx.room.Room
import br.com.breaktheice.data.BreakTheIceDatabase
import br.com.breaktheice.data.source.LocalActivityDataSource
import br.com.breaktheice.data.mapper.ActivityDTOToActivityModelMapper
import br.com.breaktheice.data.mapper.ActivityMapper
import br.com.breaktheice.data.mapper.ActivityModelToActivityDTOMapper
import br.com.breaktheice.data.source.RemoteActivityDataSource
import br.com.breaktheice.data.repository.ActivityRepositoryImpl
import br.com.breaktheice.data.utility.DATABASE_NAME
import br.com.breaktheice.data.utility.WEBSERVICE_BASEURL
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
     * Activity Mapper injection.
     */
    single {
        ActivityMapper(
            activityModelToActivityDTOMapper = ActivityModelToActivityDTOMapper(),
            activityDTOToActivityModelMapper = ActivityDTOToActivityModelMapper(),
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
    factory<IActivityRepository> {
        ActivityRepositoryImpl(
            activityMapper = get(),
            localActivityDataSource = get(),
            remoteActivityDataSource = get()
        )
    }

    /*
     * Use Case injection.
     */
    factory<ICallActivityFilteredUseCase> {
        CallActivityFilteredUseCaseImpl(activityRepository = get())
    }
    factory<ICallActivityUseCase> {
        CallActivityUseCaseImpl(activityRepository = get())
    }
    factory<IDeleteActivityUseCase> {
        DeleteActivityUseCaseImpl(activityRepository = get())
    }
    factory<IGetActivitiesByTypeUseCase> {
        GetActivitiesByTypeUseCaseImpl(activityRepository = get())
    }
    factory<IGetActivitiesUseCase> {
        GetActivitiesUseCaseImpl(activityRepository = get())
    }
    factory<IGetActivityByIdUseCase> {
        GetActivityByIdUseCaseImpl(activityRepository = get())
    }
    factory<IInsertActivityUseCase> {
        InsertActivityUseCaseImpl(activityRepository = get())
    }
    factory<IUpdateActivityFavoriteUseCase> {
        UpdateActivityFavoriteUseCaseImpl(activityRepository = get())
    }
    factory<IUpdateActivityUseCase> {
        UpdateActivityUseCaseImpl(activityRepository = get())
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
