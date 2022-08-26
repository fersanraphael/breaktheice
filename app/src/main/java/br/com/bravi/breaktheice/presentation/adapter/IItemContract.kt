package br.com.bravi.breaktheice.presentation.adapter

/**
 * @author Raphael Santos
 */
interface IItemContract<T> {

    fun replaceList(dataSet: MutableList<T>)
}
