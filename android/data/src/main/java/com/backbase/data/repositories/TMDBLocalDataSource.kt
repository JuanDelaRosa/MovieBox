package com.backbase.data.repositories

import com.backbase.domain.entities.DetailDB

interface TMDBLocalDataSource{
    suspend fun saveDetail(detail : DetailDB)
    suspend fun getDetail(id : Int) : DetailDB
}