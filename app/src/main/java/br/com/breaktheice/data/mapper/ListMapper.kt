package br.com.breaktheice.data.mapper

/**
 * @author Raphael Santos
 */
class ListMapper<in A, out B> constructor(
    private val mapper: Mapper<A, B>
) : Mapper<List<A>, List<B>> {

    override fun map(input: List<A>): List<B> {
        return input.map(mapper::map)
    }
}
