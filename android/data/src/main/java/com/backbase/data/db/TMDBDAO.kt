package com.backbase.data.db.entities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TMDBDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDetail(detail: LocalMoviesDetail)

    @Query("SELECT * FROM detail where id =:id")
    suspend fun getDetail(id : Int): LocalMoviesDetail
}