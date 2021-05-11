package com.backbase.data.api

import com.backbase.data.api.responses.MovieDetailsResult
import com.backbase.data.api.responses.NowPlayingResponse
import com.backbase.data.api.responses.PopularResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBService {
    @GET("3/movie/now_playing")
    suspend fun getNowPlaying(
        @Query("language") language : String = Config.language,
        @Query("api_key") apiKey : String = Config.apikey,
    ): Response<NowPlayingResponse>

    @GET("3/movie/popular")
    suspend fun getPopular(
        @Query("language") language : String = Config.language,
        @Query("page") page : Int = 1,
        @Query("api_key") apiKey : String = Config.apikey,
    ): Response<PopularResponse>

    @GET("3/movie/{id}}")
    suspend fun getNowPlaying(
        @Path("id") id : Int,
        @Query("language") language : String = Config.language,
        @Query("api_key") apiKey : String = Config.apikey,
    ): Response<MovieDetailsResult>
}