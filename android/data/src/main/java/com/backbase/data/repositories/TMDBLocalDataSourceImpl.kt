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
        withContext(dispatcher) {
            val tmpMovie = dao.getDetail(detail.id)
            when(type){
                MovieListType.PlayingNow ->{
                    dao.saveDetail(mapper.toLocalDetail(detail, (tmpMovie?.popular ?: false),true))
                }
                MovieListType.Popular ->{
                    dao.saveDetail(mapper.toLocalDetail(detail,true, (tmpMovie?.playingNow ?: false)))
                }
            }
        }
    }

    override suspend fun getDetail(id: Int): DetailDB =
        withContext(dispatcher) {
            val detail = dao.getDetail(id)
            return@withContext mapper.toDetailDB(detail)
        }

    override suspend fun getMovies(type: MovieListType): List<Movie> =
        withContext(dispatcher) {
            val detail = if(type == MovieListType.Popular) dao.getPopular() else dao.getPlayingNow()
            return@withContext mapper.toMovieList(detail)
        }

    override suspend fun delete(type: MovieListType) {
        withContext(dispatcher) {
            if (type == MovieListType.Popular) dao.detelePopular() else dao.detelePlayingNow()
        }
    }

}