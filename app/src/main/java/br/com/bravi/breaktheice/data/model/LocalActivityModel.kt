package br.com.bravi.breaktheice.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Raphael Santos
 */
@Entity(tableName = "activity")
data class LocalActivityModel constructor(
    @field:PrimaryKey(autoGenerate = true)
    var _id: Int,

    @field:ColumnInfo(name = "participants")
    var participants: Int,

    @field:ColumnInfo(name = "activity")
    var activity: String?,

    @field:ColumnInfo(name = "key")
    var key: String?,

    @field:ColumnInfo(name = "link")
    var link: String?,

    @field:ColumnInfo(name = "type")
    var type: String?,

    @field:ColumnInfo(name = "accessibility")
    var accessibility: Float,

    @field:ColumnInfo(name = "price")
    var price: Float
)
