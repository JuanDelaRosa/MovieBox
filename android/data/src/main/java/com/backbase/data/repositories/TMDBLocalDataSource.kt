package com.backbase.data.repositories

import com.backbase.domain.entities.DetailDB

interface TMDBLocalDataSource{
    suspend fun saveImage(detail : DetailDB)
    suspend fun getImage(id : Int) : DetailDB
}