package br.com.bravi.breaktheice.domain.entity

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

    @SerializedName(value = "participants")
    @field:ColumnInfo(name = "participants")
    var participants: Int,

    @SerializedName(value = "activity")
    @field:ColumnInfo(name = "activity")
    var activity: String?,

    @SerializedName(value = "key")
    @field:ColumnInfo(name = "key")
    var key: String?,

    @SerializedName(value = "link")
    @field:ColumnInfo(name = "link")
    var link: String?,

    @SerializedName(value = "type")
    @field:ColumnInfo(name = "type")
    var type: String?,

    @SerializedName(value = "accessibility")
    @field:ColumnInfo(name = "accessibility")
    var accessibility: Float,

    @SerializedName(value = "price")
    @field:ColumnInfo(name = "price")
    var price: Float
) {

    val isObjectValid: Boolean
        get() = key != null
}
