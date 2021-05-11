package repositories

import com.backbase.domain.common.Result
import com.backbase.domain.entities.Movie
import com.backbase.domain.repositories.TheMovieDBRepository

class TheMovieDBRepositoryImpl(private val remoteDataSource: MovieRemoteDataSource ) : TheMovieDBRepository {
    override suspend fun getNowPlaying(): Result<List<Movie>> {
       return remoteDataSource.getNowPlaying()
    }

    override suspend fun getPopular(): Result<List<Movie>> {
        return remoteDataSource.getPopular()
    }

    override suspend fun getMovie(id: Int): Result<Movie> {
        return remoteDataSource.getMovie(id)
    }
}