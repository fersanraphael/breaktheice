package br.com.breaktheice.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * @author Raphael Santos
 */
@Entity(tableName = "activity")
data class ActivityModel constructor(
    @field:PrimaryKey(autoGenerate = true)
    var _id: Int,

    @field:ColumnInfo(name = "favorite")
    var favorite: Boolean,

    @SerializedName("participants")
    @field:ColumnInfo(name = "participants")
    var participants: Int,

    @SerializedName("activity")
    @field:ColumnInfo(name = "activity")
    var activity: String?,

    @SerializedName("key")
    @field:ColumnInfo(name = "key")
    var key: String?,

    @SerializedName("link")
    @field:ColumnInfo(name = "link")
    var link: String?,

    @SerializedName("type")
    @field:ColumnInfo(name = "type")
    var type: String?,

    @SerializedName("accessibility")
    @field:ColumnInfo(name = "accessibility")
    var accessibility: Float,

    @SerializedName("price")
    @field:ColumnInfo(name = "price")
    var price: Float
) {

    val isObjectValid: Boolean
        get() = key != null
}
