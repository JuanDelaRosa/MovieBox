package com.backbase.data.mappers

import com.backbase.data.db.entities.LocalMoviesDetail
import com.backbase.domain.entities.DetailDB
import com.backbase.domain.entities.Genre

class TMDBLocalMapper {
    fun toImageDB(response : LocalMoviesDetail?) : DetailDB{
        return if(response==null)
            DetailDB(0,"0", Genre(1,""))
        else DetailDB(response.id,response.runtime,response.genre)
    }
    fun toImage(response : DetailDB) : LocalMoviesDetail{
        return LocalMoviesDetail(response.id,response.runtime,response.genre)
    }
}