package com.backbase.domain.entities

data class Movie(
    val id: Int,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val overview: String,
    var runtime: Int,
)

