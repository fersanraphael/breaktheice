package br.com.breaktheice.domain.entity

/**
 * @author Raphael Santos
 */
data class ActivityModel constructor(
    var id: Int,
    var favorite: Boolean,
    var participants: Int,
    var activity: String?,
    var key: String?,
    var link: String?,
    var type: String?,
    var accessibility: Float,
    var price: Float
) {

    val isObjectValid: Boolean
        get() = key != null
}
