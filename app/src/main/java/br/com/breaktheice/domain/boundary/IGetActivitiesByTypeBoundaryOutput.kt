package br.com.breaktheice.domain.boundary

import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
interface IGetActivitiesByTypeBoundaryOutput {

    suspend operator fun invoke(type: String): MutableList<ActivityModel>?
}
