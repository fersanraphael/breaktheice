package br.com.breaktheice.domain.boundary

import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
interface IDeleteActivityBoundaryOutput {

    suspend operator fun invoke(activityModel: ActivityModel)
}
