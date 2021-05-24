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
    suspend fun getDetail(id : Int): LocalMoviesDetail?

    @Query("SELECT * FROM detail where popular = 1")
    suspend fun getPopular(): List<LocalMoviesDetail>?

    @Query("SELECT * FROM detail where playingNow = 1")
    suspend fun getPlayingNow(): List<LocalMoviesDetail>?

    @Query("Update detail set popular = 0 where popular = 1")
    suspend fun detelePopular()

    @Query("Update detail set playingNow = 0 where playingNow = 1")
    suspend fun detelePlayingNow()
}