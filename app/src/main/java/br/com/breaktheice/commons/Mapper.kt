package br.com.breaktheice.commons

/**
 * @author Raphael Santos
 */
interface Mapper<in A, out B> {

    fun map(input: A): B
}
