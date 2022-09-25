package br.com.breaktheice.domain.boundary

import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
interface IGetActivitiesBoundaryOutput {

    suspend operator fun invoke(): MutableList<ActivityModel>?
}
