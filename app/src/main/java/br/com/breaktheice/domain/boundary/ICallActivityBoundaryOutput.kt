package br.com.breaktheice.domain.boundary

import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
interface ICallActivityBoundaryOutput {

    suspend operator fun invoke(): ActivityModel?
}
