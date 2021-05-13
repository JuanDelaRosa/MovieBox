package com.backbase.data.repositories

import com.backbase.data.api.TheMovieDBService
import com.backbase.data.mappers.TheMovieDBMapper
import com.backbase.domain.common.Result
import com.backbase.domain.entities.Movie
import com.backbase.domain.repositories.TheMovieDBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MovieRemoteDataSource{
    suspend fun getNowPlaying(): Result<List<Movie>>
    suspend fun getPopular(page : Int): Result<List<Movie>>
    suspend fun getMovie(id: Int): Result<Movie>
}