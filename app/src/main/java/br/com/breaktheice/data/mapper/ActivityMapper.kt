package br.com.breaktheice.data.mapper

import br.com.breaktheice.data.model.LocalActivityModel
import br.com.breaktheice.data.model.RemoteActivityModel
import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */

data class ActivityMapper constructor(
    val activityToLocalActivityMapper: ActivityToLocalActivityMapper,
    val activityToRemoteActivityMapper: ActivityToRemoteActivityMapper,
    val localActivityToActivityMapper: LocalActivityToActivityMapper,
    val remoteActivityToActivityMapper: RemoteActivityToActivityMapper
)

/**
 * Transforms ActivityModel into LocalActivityModel.
 */
class ActivityToLocalActivityMapper : Mapper<ActivityModel, LocalActivityModel> {

    override fun map(input: ActivityModel): LocalActivityModel {
        return LocalActivityModel(
            _id = input.id,
            favorite = input.favorite,
            participants = input.participants,
            activity = input.activity,
            key = input.key,
            link = input.link,
            type = input.type,
            accessibility = input.accessibility,
            price = input.price
        )
    }
}

/**
 * Transforms ActivityModel into RemoteActivityModel.
 */
class ActivityToRemoteActivityMapper : Mapper<ActivityModel, RemoteActivityModel> {

    override fun map(input: ActivityModel): RemoteActivityModel {
        return RemoteActivityModel(
            participants = input.participants,
            activity = input.activity,
            key = input.key,
            link = input.link,
            type = input.type,
            accessibility = input.accessibility,
            price = input.price
        )
    }
}

/**
 * Transforms LocalActivityModel into ActivityModel.
 */
class LocalActivityToActivityMapper : Mapper<LocalActivityModel, ActivityModel> {

    override fun map(input: LocalActivityModel): ActivityModel {
        return ActivityModel(
            id = input._id,
            favorite = input.favorite,
            participants = input.participants,
            activity = input.activity,
            key = input.key,
            link = input.link,
            type = input.type,
            accessibility = input.accessibility,
            price = input.price
        )
    }
}

/**
 * Transforms RemoteActivityModel into ActivityModel.
 */
class RemoteActivityToActivityMapper : Mapper<RemoteActivityModel, ActivityModel> {

    override fun map(input: RemoteActivityModel): ActivityModel {
        return ActivityModel(
            id = 0,
            favorite = false,
            participants = input.participants,
            activity = input.activity,
            key = input.key,
            link = input.link,
            type = input.type,
            accessibility = input.accessibility,
            price = input.price
        )
    }
}
