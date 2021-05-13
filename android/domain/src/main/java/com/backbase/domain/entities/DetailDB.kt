package com.backbase.domain.entities

data class DetailDB(
    var id : Int,
    var runtime: Int,
    var genre: List<Genre>)
