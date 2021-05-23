package com.backbase.data.mappers
import com.backbase.data.api.responses.*
import com.backbase.domain.entities.*
import com.backbase.domain.entities.Collection

class TheMovieDBMapper {

    fun toMovieList(response : ApiResponse?) : List<Movie>{
        return if (response != null) {
            response.results?.map {
                Movie(
                    it.id ?: -1,
                    it.poster_path ?: "",
                    it.release_date ?: "",
                    it.title ?: "",
                    it.vote_average ?: -1.0,
                    it.overview ?: "",
                    0,
                    emptyList()
                )
            } ?: emptyList()
        } else emptyList()
    }

    fun toDetailedMovie(mDetail : MovieDetailsResult?) : Movie{
        return if (mDetail != null) {
            Movie(
                mDetail.id ?: -1,
                mDetail.poster_path ?: "",
                mDetail.release_date ?: "",
                mDetail.title ?: "",
                mDetail.vote_average ?: -1.0,
                mDetail.overview ?: "",
                mDetail.runtime ?: -1,
                GenresMapper(mDetail.genres)
            )
        } else Movie(-1,"","","",-1.0,"",-1, emptyList())
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