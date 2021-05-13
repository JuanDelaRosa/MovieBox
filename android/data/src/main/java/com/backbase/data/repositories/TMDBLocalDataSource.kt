package com.backbase.data.repositories

import com.backbase.domain.entities.ImageDB

interface TMDBLocalDataSource{
    suspend fun saveImage(image : ImageDB)
    suspend fun getImage(id : Int) : ImageDB
}