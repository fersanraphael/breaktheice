package br.com.breaktheice.domain.boundary

import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
interface ICallActivityFilteredBoundaryOutput {

    suspend operator fun invoke(options: MutableMap<String, String>): ActivityModel?
}
