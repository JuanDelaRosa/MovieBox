package com.backbase.data.repositories

import android.util.Log
import com.backbase.data.api.TheMovieDBService
import com.backbase.data.api.responses.ApiResponse
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
                    return@withContext Result.Success(getDetail(response.body()!!))
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
                    return@withContext Result.Success(getDetail(response.body()!!))
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }

    private suspend fun getDetail(body: ApiResponse) : List<Movie> = withContext(Dispatchers.IO){
        val movielist = mapper.toMovieList(body)
        movielist.forEach {
            try {
                val dbresult = localdata.getDetail(it.id)
                if(dbresult.id==0) {
                    val result = service.getMovie(id = it.id)
                    if (result.isSuccessful) {
                        val castResult = mapper.toDetailedMovie(result.body()!!)
                        it.genre = castResult.genre
                        it.runtime = castResult.runtime
                        localdata.saveDetail(DetailDB(it.id,it.runtime,it.genre))
                    }
                }else{
                    it.genre =dbresult.genre
                    it.runtime =dbresult.runtime
                }
            }catch (e : Exception){
                Log.d("getPopular", "problem parsing $it.id movie")
            }
        }
        return@withContext movielist
    }

    override suspend fun getMovie(id: Int): Result<Movie> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getMovie(id)
                if (response.isSuccessful) {
                    return@withContext Result.Success(mapper.toDetailedMovie(response.body()!!))
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
}