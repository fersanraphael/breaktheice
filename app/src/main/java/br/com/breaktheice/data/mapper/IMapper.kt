package br.com.breaktheice.data.mapper

/**
 * @author Raphael Santos
 */
interface IMapper<in A, out B> {

    fun map(input: A): B
}
