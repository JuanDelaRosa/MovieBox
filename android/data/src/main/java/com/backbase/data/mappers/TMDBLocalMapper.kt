package com.backbase.data.mappers

import com.backbase.data.db.entities.LocalMoviesDetail
import com.backbase.domain.entities.DetailDB
import com.google.gson.Gson

class TMDBLocalMapper {
    fun toDetailDB(response : LocalMoviesDetail?) : DetailDB{
        return if(response==null)
            DetailDB(0,0, emptyList())
        else {
            Gson().fromJson(response.info, DetailDB::class.java)
        }
    }
    fun toLocalDetail(response : DetailDB) : LocalMoviesDetail{
        val json = Gson().toJson(response)
        return LocalMoviesDetail(response.id,json)
    }
}