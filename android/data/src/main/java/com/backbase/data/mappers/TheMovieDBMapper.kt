package com.backbase.data.mappers
import com.backbase.data.api.responses.*
import com.backbase.domain.entities.*
import com.backbase.domain.entities.Collection

class TheMovieDBMapper {

    fun toMovieList(response : ApiResponse) : List<Movie>{
        return response.results?.map {
            Movie(
                it.adult ?: false,
                it.backdrop_path ?: "",
                it.genre_ids ?: emptyList(),
                it.id ?: -1,
                it.original_language ?: "",
                it.original_title ?: "",
                it.overview ?: "",
                it.popularity ?: -1.0,
                it.poster_path ?: "",
                it.release_date ?: "",
                it.title ?: "",
                it.video ?: false,
                it.vote_average ?: -1.0,
                it.vote_count ?: -1,
                null
            )
        } ?: emptyList()
    }

    fun toDetailedMovie(it : MovieDetailsResult) : Movie{
        return Movie(
            it.adult ?: false,
            it.backdrop_path ?: "",
            emptyList(),
            it.id ?: -1,
            it.original_language ?: "",
            it.original_title ?: "",
            it.overview ?: "",
            it.popularity ?: -1.0,
            it.poster_path ?: "",
            it.release_date ?: "",
            it.title ?: "",
            it.video ?: false,
            it.vote_average ?: -1.0,
            it.vote_count ?: -1,
            Detail(
                CollectionMapper(it.belongs_to_collection),
                it.budget ?: -1,
                GenresMapper(it.genres),
                it.homepage ?: "",
                it.imdb_id ?: "",
                CompaniesMapper(it.production_companies),
                CountryMapper(it.production_countries),
                it.revenue ?: -1,
                it.runtime ?: -1,
                LanguagesMapper(it.spoken_languages),
                it.status ?: "",
                it.tagline ?: ""
            )
        )
    }
    private fun CollectionMapper(collection : BelongsToCollection?) : Collection{
        return if(collection!=null) {
            Collection(
                collection.backdrop_path ?: "",
                collection.id ?: -1,
                collection.name ?: "",
                collection.poster_path ?: ""
            )
        } else Collection("",-1,"","")
    }
    private fun GenresMapper(list : List<GenreApi>?) : List<Genre> {
        return list?.map {
            Genre(
                it.id ?: -1,
                it.name ?: "",
            )
        } ?: emptyList()
    }
    private fun CompaniesMapper(list : List<ProductionCompanyApi>?) : List<ProductionCompany>{
        return list?.map {
            ProductionCompany(
                it.id ?: -1,
                it.logo_path ?: "",
                it.name ?: "",
                it.origin_country ?: "",
            )
        } ?: emptyList()
    }
    private fun CountryMapper(list : List<ProductionCountryApi>?) : List<ProductionCountry>{
        return list?.map {
            ProductionCountry(
                it.iso_3166_1 ?: "",
                it.name ?: "",
            )
        } ?: emptyList()
    }
    private fun LanguagesMapper(list : List<SpokenLanguageApi>?) : List<SpokenLanguage>{
        return list?.map {
            SpokenLanguage(
                it.english_name ?: "",
                it.iso_639_1 ?: "",
                it.name ?: "",
            )
        } ?: emptyList()
    }

}