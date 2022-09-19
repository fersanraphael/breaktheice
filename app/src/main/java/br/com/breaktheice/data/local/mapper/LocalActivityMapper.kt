package br.com.breaktheice.data.local.mapper

import br.com.breaktheice.data.local.model.LocalActivityModel
import br.com.breaktheice.data.mapper.IMapper
import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */

/**
 * Transforms ActivityModel into LocalActivityModel.
 */
class ActivityToLocalActivityMapper : IMapper<ActivityModel, LocalActivityModel> {

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
 * Transforms LocalActivityModel into ActivityModel.
 */
class LocalActivityToActivityMapper : IMapper<LocalActivityModel, ActivityModel> {

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
