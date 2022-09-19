package br.com.breaktheice.data.mapper

/**
 * @author Raphael Santos
 */

interface IMapper<in A, out B> {

    fun map(input: A): B
}

class ListMapper<in A, out B> constructor(
    private val mapper: IMapper<A, B>
) : IMapper<List<A>, List<B>> {

    override fun map(input: List<A>): List<B> {
        return input.map(mapper::map)
    }
}
