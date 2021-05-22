package com.backbase.data.repositories

import com.backbase.data.db.entities.MovieListType
import com.backbase.data.db.entities.TMDBDAO
import com.backbase.data.mappers.TMDBLocalMapper
import com.backbase.domain.entities.DetailDB
import com.backbase.domain.entities.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TMDBLocalDataSourceImpl(
    private val dao: TMDBDAO,
    private val dispatcher: CoroutineDispatcher,
    private val mapper : TMDBLocalMapper
) : TMDBLocalDataSource{

    override suspend fun saveDetail(detail: DetailDB, type: MovieListType) {
       dao.saveDetail(mapper.toLocalDetail(detail,type))
    }

    override suspend fun getDetail(id: Int): DetailDB =
        withContext(dispatcher) {
            val detail = dao.getDetail(id)
            return@withContext mapper.toDetailDB(detail)
        }

    override suspend fun getMovies(popular : Boolean): List<Movie> =
        withContext(dispatcher) {
            val detail = dao.getMovies(popular)
            return@withContext mapper.toMovieList(detail)
        }

    override suspend fun delete(popular: Boolean) {
        dao.detele(popular)
    }

}