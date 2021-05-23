package com.backbase.data.mappers
import com.backbase.data.api.responses.*
import com.backbase.domain.entities.*
import com.backbase.domain.entities.Collection

class TheMovieDBMapper {

    fun toMovieList(response : ApiResponse?) : List<Movie>{
        return if (response != null) {
            response.results?.map {
                Movie(
                    it.id ?: -1,
                    it.poster_path ?: "",
                    it.release_date ?: "",
                    it.title ?: "",
                    it.vote_average ?: -1.0,
                    it.overview ?: "",
                    0,
                    emptyList()
                )
            } ?: emptyList()
        } else emptyList()
    }

    fun toDetailedMovie(mDetail : MovieDetailsResult?) : Movie{
        return if (mDetail != null) {
            Movie(
                mDetail.id ?: -1,
                mDetail.poster_path ?: "",
                mDetail.release_date ?: "",
                mDetail.title ?: "",
                mDetail.vote_average ?: -1.0,
                mDetail.overview ?: "",
                mDetail.runtime ?: -1,
                GenresMapper(mDetail.genres)
            )
        } else Movie(-1,"","","",-1.0,"",-1, emptyList())
    }
    private fun GenresMapper(list : List<GenreApi>?) : List<Genre> {
        return list?.map {
            Genre(
                it.id ?: -1,
                it.name ?: "",
            )
        } ?: emptyList()
    }
}