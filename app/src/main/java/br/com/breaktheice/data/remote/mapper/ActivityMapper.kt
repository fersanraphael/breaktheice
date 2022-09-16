package br.com.breaktheice.data.remote.mapper

import br.com.breaktheice.data.mapper.Mapper
import br.com.breaktheice.data.remote.model.RemoteActivityModel
import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */

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
