package br.com.bravi.breaktheice.presentation.adapter

/**
 * @author Raphael Santos
 */
interface IItemContract<T> {

    fun addItem(data: T)

    fun removeItem(data: T)

    fun replaceList(dataSet: MutableList<T>)
}
