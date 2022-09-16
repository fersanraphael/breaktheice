package br.com.breaktheice.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * @author Raphael Santos
 */
data class RemoteActivityModel constructor(
    @SerializedName("participants")
    var participants: Int,

    @SerializedName("activity")
    var activity: String?,

    @SerializedName("key")
    var key: String?,

    @SerializedName("link")
    var link: String?,

    @SerializedName("type")
    var type: String?,

    @SerializedName("accessibility")
    var accessibility: Float,

    @SerializedName("price")
    var price: Float
)
