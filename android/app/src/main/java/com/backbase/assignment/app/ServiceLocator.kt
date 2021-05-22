package com.backbase.assignment.app

import android.content.Context
import com.backbase.assignment.BuildConfig
import com.backbase.data.api.NetworkModule
import com.backbase.data.db.entities.TMDBDataBase
import com.backbase.data.mappers.TMDBLocalMapper
import com.backbase.data.mappers.TheMovieDBMapper
import com.backbase.data.repositories.MovieRemoteDataSourceImpl
import com.backbase.data.repositories.TMDBLocalDataSourceImpl
import com.backbase.data.repositories.TheMovieDBRepositoryImpl
import com.backbase.domain.repositories.TheMovieDBRepository
import kotlinx.coroutines.Dispatchers

object ServiceLocator {

    private val networkModule by lazy {
        NetworkModule()
    }

    @Volatile
    var repository: TheMovieDBRepository? = null
    private var database: TMDBDataBase? = null
    private val localMapper by lazy { TMDBLocalMapper() }

    fun provideTMDBRepository(context: Context): TheMovieDBRepository {
        synchronized(this) {
            return repository ?: createRepository(context)
        }
    }

    private fun createRepository(context: Context): TheMovieDBRepository {
        val newRepo =
            TheMovieDBRepositoryImpl(
                MovieRemoteDataSourceImpl(
                    networkModule.createTheMovieDBAPI(" https://api.themoviedb.org/3/","en-US",BuildConfig.MDBAPIKey),
                    TheMovieDBMapper(),
                    createTMDBLocalDataSource(context)
                )
            )
        repository = newRepo
        return newRepo
    }

    private fun createTMDBLocalDataSource(context: Context) : TMDBLocalDataSourceImpl {
        val database = database ?: createDataBase(context)
        return TMDBLocalDataSourceImpl(
            database.dao(),
            Dispatchers.IO,
            localMapper
        )
    }

    private fun createDataBase(context: Context): TMDBDataBase {
        val result = TMDBDataBase.getDataBase(context)
        database = result
        return result
    }
}