package com.backbase.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//@Entity(tableName = "images")
data class LocalMoviesDetail(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var base64: String
)
