package com.backbase.data.mappers

import com.backbase.data.db.entities.LocalMoviesDetail
import com.backbase.data.db.entities.MovieListType
import com.backbase.domain.entities.DetailDB
import com.backbase.domain.entities.Movie
import com.google.gson.Gson

class TMDBLocalMapper {
    fun toDetailDB(response : LocalMoviesDetail?) : DetailDB{
        return if(response==null)
            DetailDB(0,0,"","","","",0.00, emptyList())
        else {
            Gson().fromJson(response.info, DetailDB::class.java)
        }
    }

    fun toMovieList(list: List<LocalMoviesDetail>?) : List<Movie>{
        return list?.map {
            DetailtoMovie(toDetailDB(it))
        } ?: emptyList()
    }

    private fun DetailtoMovie(detail : DetailDB) : Movie{
        return Movie(detail.id,detail.image,detail.release,detail.title,detail.popularity,detail.description,detail.runtime,detail.genre)
    }

    fun toLocalDetail(response : DetailDB, popular : Boolean, playingNow : Boolean) : LocalMoviesDetail{
        val json = Gson().toJson(response)
        return LocalMoviesDetail(response.id,json,popular,playingNow)
    }
}