package com.backbase.data.db.entities

import androidx.room.PrimaryKey
import com.backbase.domain.entities.Genre

//@Entity(tableName = "detail")
data class LocalMoviesDetail(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var runtime: String,
    var genre: Genre
)
