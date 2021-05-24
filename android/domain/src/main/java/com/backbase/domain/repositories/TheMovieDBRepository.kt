package com.backbase.domain.repositories

import com.backbase.domain.common.Result
import com.backbase.domain.entities.Movie

interface TheMovieDBRepository {
    suspend fun getNowPlaying(): Result<List<Movie>>
    suspend fun getPopular(page : Int): Result<List<Movie>>
    suspend fun getFromLocalDB(popular : Int): Result<List<Movie>>
}