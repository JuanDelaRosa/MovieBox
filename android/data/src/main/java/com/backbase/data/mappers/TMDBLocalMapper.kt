package com.backbase.data.mappers

import com.backbase.data.db.entities.LocalMoviesDetail
import com.backbase.domain.entities.ImageDB

class TMDBLocalMapper {
    fun toImageDB(response : LocalMoviesDetail?) : ImageDB{
        return if(response==null)
            ImageDB(0,"")
        else ImageDB(response.id,response.base64)
    }
    fun toImage(response : ImageDB) : LocalMoviesDetail{
        return LocalMoviesDetail(response.id,response.base64)
    }
}