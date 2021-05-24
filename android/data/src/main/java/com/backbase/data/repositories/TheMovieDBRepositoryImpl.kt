package com.backbase.data.repositories

import com.backbase.data.db.entities.MovieListType
import com.backbase.domain.common.Result
import com.backbase.domain.entities.Movie
import com.backbase.domain.repositories.TheMovieDBRepository

class TheMovieDBRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSourceImpl
) : TheMovieDBRepository {
    override suspend fun getNowPlaying(): Result<List<Movie>> {
       return remoteDataSource.getNowPlaying()
    }

    override suspend fun getPopular(page : Int): Result<List<Movie>> {
        return remoteDataSource.getPopular(page)
    }

    override suspend fun getFromLocalDB(popular: Int): Result<List<Movie>> {
        val typeToBool = if(popular ==1) MovieListType.Popular else MovieListType.PlayingNow
        return remoteDataSource.getFromLocalDB(typeToBool)
    }
}