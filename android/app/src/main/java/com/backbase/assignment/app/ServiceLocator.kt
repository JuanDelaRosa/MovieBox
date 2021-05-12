package com.backbase.assignment.app

import com.backbase.data.api.NetworkModule
import com.backbase.domain.repositories.TheMovieDBRepository
import mappers.TheMovieDBMapper
import repositories.MovieRemoteDataSource
import repositories.TheMovieDBRepositoryImpl

object ServiceLocator {
    private val networkModule by lazy {
        NetworkModule()
    }

    @Volatile
    var repository: TheMovieDBRepository? = null

    fun providePokeRepository(): TheMovieDBRepository {
        synchronized(this) {
            return repository ?: createRepository()
        }
    }

    private fun createRepository(): TheMovieDBRepository {
        val newRepo =
            TheMovieDBRepositoryImpl(
                MovieRemoteDataSource(
                    networkModule.createTheMovieDBAPI(" https://api.themoviedb.org/3/","en-US","55957fcf3ba81b137f8fc01ac5a31fb5"),
                    TheMovieDBMapper()
                )
            )
        repository = newRepo
        return newRepo
    }
}