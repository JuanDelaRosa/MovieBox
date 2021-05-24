package com.backbase.data.repositories

import com.backbase.data.db.entities.MovieListType
import com.backbase.domain.entities.DetailDB
import com.backbase.domain.entities.Movie

interface TMDBLocalDataSource{
    suspend fun saveDetail(detail: DetailDB, type: MovieListType)
    suspend fun getDetail(id : Int) : DetailDB
    suspend fun getMovies(type: MovieListType): List<Movie>
    suspend fun delete(type: MovieListType)
}