package br.com.breaktheice.domain.boundary

import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
interface IGetActivityByIdBoundaryOutput {

    suspend operator fun invoke(id: Int): ActivityModel?
}
