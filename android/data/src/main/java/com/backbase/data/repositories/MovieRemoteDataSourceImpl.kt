package com.backbase.data.repositories

import android.util.Log
import com.backbase.data.api.TheMovieDBService
import com.backbase.data.api.responses.ApiResponse
import com.backbase.data.db.entities.MovieListType
import com.backbase.data.mappers.TheMovieDBMapper
import com.backbase.domain.common.Result
import com.backbase.domain.entities.DetailDB
import com.backbase.domain.entities.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRemoteDataSourceImpl(private val service: TheMovieDBService, private val mapper: TheMovieDBMapper,private val localdata: TMDBLocalDataSourceImpl) : MovieRemoteDataSource {
    override suspend fun getNowPlaying(): Result<List<Movie>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getNowPlaying()
                if (response.isSuccessful) {
                    return@withContext Result.Success(getDetail(response.body(), MovieListType.PlayingNow))
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }

    override suspend fun getPopular(page : Int): Result<List<Movie>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getPopular(page = page)
                if (response.isSuccessful) {
                    return@withContext Result.Success(getDetail(response.body(),MovieListType.Popular))
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }

    override suspend fun getFromLocalDB(popular: Boolean): Result<List<Movie>> =
        withContext(Dispatchers.IO){
            try {
                val response = localdata.getMovies(popular)
                if (response.count()>0) {
                    return@withContext Result.Success(response)
                } else {
                    return@withContext Result.Error(Exception("You're offline. Check your connection"))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
    }

    private suspend fun getDetail(body: ApiResponse?, type : MovieListType) : List<Movie> = withContext(Dispatchers.IO){
        val movielist = mapper.toMovieList(body)
        val typetoBool = when (type){
            MovieListType.PlayingNow ->  false
            MovieListType.Popular -> true
        }
        localdata.delete(typetoBool)
        movielist.forEach {
            try {
                val result = service.getMovie(id = it.id)
                if (result.isSuccessful) {
                    val castResult = mapper.toDetailedMovie(result.body())
                    it.genre = castResult.genre
                    it.runtime = castResult.runtime
                    localdata.saveDetail(DetailDB(it.id,it.runtime,it.posterPath,it.title,it.releaseDate,it.overview,it.voteAverage,it.genre),type)
                }
            }catch (e : Exception){
                Log.d("getPopular", "problem parsing $it.id movie")
            }
        }
        return@withContext movielist
    }
}