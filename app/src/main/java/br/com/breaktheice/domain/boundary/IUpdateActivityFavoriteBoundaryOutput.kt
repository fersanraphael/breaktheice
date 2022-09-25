package br.com.breaktheice.domain.boundary

/**
 * @author Raphael Santos
 */
interface IUpdateActivityFavoriteBoundaryOutput {

    suspend operator fun invoke(
        id: Int,
        favorite: Boolean
    )
}
