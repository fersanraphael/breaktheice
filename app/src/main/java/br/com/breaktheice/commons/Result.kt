package br.com.breaktheice.commons

/**
 * @author Raphael Santos
 */
sealed class Result<out A, out B> {

    data class Success<out A>(
        val value: A
    ) : Result<A, Nothing>()

    data class Failure<out B>(
        val value: B? = null
    ) : Result<Nothing, B>()

    data class Error(
        val throwable: Throwable?
    ) : Result<Nothing, Nothing>()

    object Loading : Result<Nothing, Nothing>()
}
