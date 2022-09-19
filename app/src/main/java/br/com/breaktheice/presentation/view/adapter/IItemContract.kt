package br.com.breaktheice.presentation.view.adapter

/**
 * @author Raphael Santos
 */
interface IItemContract<T> {

    fun replaceList(dataSet: List<T>)
}
