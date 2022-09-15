package br.com.breaktheice.domain.common

/**
 * @author Raphael Santos
 */
sealed class Result<out A> {

    data class Success<out A>(
        val value: A
    ) : Result<A>()

    object Failure : Result<Nothing>()

    data class Error(
        val throwable: Throwable?
    ) : Result<Nothing>()

    object Loading : Result<Nothing>()
}
