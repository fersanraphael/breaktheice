package br.com.breaktheice.data.mapper

/**
 * @author Raphael Santos
 */
interface Mapper<in A, out B> {

    fun map(input: A): B
}
