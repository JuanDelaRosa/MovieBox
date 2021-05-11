package com.backbase.data.api.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieDetailsResult(
    @Expose @SerializedName("adult") val adult: Boolean?,
    @Expose @SerializedName("backdrop_path") val backdrop_path: String?,
    @Expose @SerializedName("belongs_to_collection") val belongs_to_collection: BelongsToCollection?,
    @Expose @SerializedName("budget") val budget: Int?,
    @Expose @SerializedName("genres") val genres: List<Genre>?,
    @Expose @SerializedName("homepage") val homepage: String?,
    @Expose @SerializedName("id") val id: Int?,
    @Expose @SerializedName("imdb_id") val imdb_id: String?,
    @Expose @SerializedName("original_language") val original_language: String?,
    @Expose @SerializedName("original_title") val original_title: String?,
    @Expose @SerializedName("overview") val overview: String?,
    @Expose @SerializedName("popularity") val popularity: Double?,
    @Expose @SerializedName("poster_path") val poster_path: String?,
    @Expose @SerializedName("production_companies") val production_companies: List<ProductionCompany>?,
    @Expose @SerializedName("production_countries") val production_countries: List<ProductionCountry>?,
    @Expose @SerializedName("release_date") val release_date: String?,
    @Expose @SerializedName("revenue") val revenue: Int?,
    @Expose @SerializedName("runtime") val runtime: Int?,
    @Expose @SerializedName("spoken_languages") val spoken_languages: List<SpokenLanguage>?,
    @Expose @SerializedName("status") val status: String?,
    @Expose @SerializedName("tagline") val tagline: String?,
    @Expose @SerializedName("title") val title: String?,
    @Expose @SerializedName("video") val video: Boolean?,
    @Expose @SerializedName("vote_average") val vote_average: Double?,
    @Expose @SerializedName("vote_count") val vote_count: Int?
)

data class BelongsToCollection(
    @Expose @SerializedName("backdrop_path") val backdrop_path: String?,
    @Expose @SerializedName("id") val id: Int?,
    @Expose @SerializedName("name") val name: String?,
    @Expose @SerializedName("poster_path") val poster_path: String?
)

data class Genre(
    @Expose @SerializedName("id") val id: Int?,
    @Expose @SerializedName("name") val name: String?
)

data class ProductionCompany(
    @Expose @SerializedName("id") val id: Int?,
    @Expose @SerializedName("logo_path") val logo_path: String?,
    @Expose @SerializedName("name") val name: String?,
    @Expose @SerializedName("origin_country") val origin_country: String?
)

data class ProductionCountry(
    @Expose @SerializedName("iso_3166_1") val iso_3166_1: String?,
    @Expose @SerializedName("name") val name: String?
)

data class SpokenLanguage(
    @Expose @SerializedName("english_name") val english_name: String?,
    @Expose @SerializedName("iso_639_1") val iso_639_1: String?,
    @Expose @SerializedName("name") val name: String?
)