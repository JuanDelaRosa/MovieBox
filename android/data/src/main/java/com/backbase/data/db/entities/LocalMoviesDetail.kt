package com.backbase.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail")
data class LocalMoviesDetail(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var info: String,
    @ColumnInfo(defaultValue = "0")
    var popular : Boolean,
    @ColumnInfo(defaultValue = "0")
    var playingNow : Boolean
)
