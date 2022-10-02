package br.com.breaktheice.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * @author Raphael Santos
 */
@Entity(tableName = "activity")
data class ActivityDTO constructor(
    @field:PrimaryKey(autoGenerate = true)
    var _id: Int,

    @field:ColumnInfo(name = "favorite")
    var favorite: Boolean,

    @field:ColumnInfo(name = "participants")
    @SerializedName("participants")
    var participants: Int,

    @field:ColumnInfo(name = "activity")
    @SerializedName("activity")
    var activity: String?,

    @field:ColumnInfo(name = "key")
    @SerializedName("key")
    var key: String?,

    @field:ColumnInfo(name = "link")
    @SerializedName("link")
    var link: String?,

    @field:ColumnInfo(name = "type")
    @SerializedName("type")
    var type: String?,

    @field:ColumnInfo(name = "accessibility")
    @SerializedName("accessibility")
    var accessibility: Float,

    @field:ColumnInfo(name = "price")
    @SerializedName("price")
    var price: Float
)
