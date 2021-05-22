package com.backbase.domain.entities

data class DetailDB(
    var id : Int,
    var runtime: Int,
    var image: String,
    var title: String,
    var release: String,
    var description: String,
    var popularity: Double,
    var genre: List<Genre>)
