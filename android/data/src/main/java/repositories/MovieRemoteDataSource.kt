package repositories

import com.backbase.data.api.TheMovieDBService
import com.backbase.domain.common.Result
import com.backbase.domain.entities.Movie
import com.backbase.domain.repositories.TheMovieDBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mappers.TheMovieDBMapper

class MovieRemoteDataSource(private val service: TheMovieDBService, private val mapper: TheMovieDBMapper) : TheMovieDBRepository {
    override suspend fun getNowPlaying(): Result<List<Movie>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getNowPlaying()
                if (response.isSuccessful) {
                    return@withContext Result.Success(mapper.toMovieList(response.body()!!))
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }

    override suspend fun getPopular(): Result<List<Movie>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getPopular()
                if (response.isSuccessful) {
                    return@withContext Result.Success(mapper.toMovieList(response.body()!!))
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
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