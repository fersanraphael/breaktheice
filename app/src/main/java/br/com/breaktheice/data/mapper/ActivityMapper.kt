package br.com.breaktheice.data.mapper

import br.com.breaktheice.data.model.ActivityDTO
import br.com.breaktheice.domain.model.ActivityModel

/**
 * @author Raphael Santos
 */
data class ActivityMapper constructor(
    val activityModelToActivityDTOMapper: IMapper<ActivityModel, ActivityDTO>,
    val activityDTOToActivityModelMapper: IMapper<ActivityDTO, ActivityModel>
)

/**
 * Transforms ActivityModel into ActivityDTO.
 */
class ActivityModelToActivityDTOMapper : IMapper<ActivityModel, ActivityDTO> {

    override fun map(input: ActivityModel): ActivityDTO {
        return ActivityDTO(
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
 * Transforms ActivityDTO into ActivityModel.
 */
class ActivityDTOToActivityModelMapper : IMapper<ActivityDTO, ActivityModel> {

    override fun map(input: ActivityDTO): ActivityModel {
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
