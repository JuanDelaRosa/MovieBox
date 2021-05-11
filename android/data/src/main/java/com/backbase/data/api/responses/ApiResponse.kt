package com.backbase.data.api.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @Expose @SerializedName("dates") val dates: Dates?,
    @Expose @SerializedName("page") val page: Int?,
    @Expose @SerializedName("results") val results: List<ResultApi>?,
    @Expose @SerializedName("total_pages") val total_pages: Int?,
    @Expose @SerializedName("total_results") val total_results: Int?
)

data class Dates(
    @Expose @SerializedName("maximum") val maximum: String?,
    @Expose @SerializedName("minimum") val minimum: String?
)