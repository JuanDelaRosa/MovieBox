package com.backbase.data.repositories

import com.backbase.data.db.entities.MovieListType
import com.backbase.domain.common.Result
import com.backbase.domain.entities.Movie

interface MovieRemoteDataSource{
    suspend fun getNowPlaying(): Result<List<Movie>>
    suspend fun getPopular(page : Int): Result<List<Movie>>
    suspend fun getFromLocalDB(type: MovieListType): Result<List<Movie>>
}