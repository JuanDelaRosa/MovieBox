package com.backbase.domain.entities

data class Detail(
    val collection: Collection?,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val imdbId: String,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
)


