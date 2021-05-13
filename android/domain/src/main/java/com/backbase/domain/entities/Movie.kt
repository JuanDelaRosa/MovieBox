package com.backbase.domain.entities

data class Movie(
    val id: Int,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val overview: String,
    var runtime: Int,
    var genre: List<Genre>
){
    fun imageUrl(size : String = "w200") : String{
        return "https://image.tmdb.org/t/p/$size/$posterPath"
    }

    fun DurationDetail() :String{
        return "${ if(ReleaseDateFix()!="-") ReleaseDateFix() else "" }  - ${ if(DurationFix()!="-") DurationFix() else "" }"
    }
    fun TitleFix(): String {
        return if(title.length >28){
            title.substring(0,25)+"..."
        }else title
    }
    fun DurationFix() : String {
        return if(runtime>0){
            val horas : Int = runtime/60
            val min : Int = runtime - horas*60
            "${horas}h ${min}m"
        }else "-"
    }
    fun ReleaseDateFix(): String {
        if (!releaseDate.contains("-"))
            return "-"
        val dates = releaseDate.split("-")
        if(dates.count()!=3)
            return "-"
        val month = when (dates[1]) {
            "01" -> "January"
            "02" -> "February"
            "03" -> "March"
            "04" -> "April"
            "05" -> "May"
            "06" -> "June"
            "07" -> "July"
            "08" -> "August"
            "09" -> "September"
            "10" -> "October"
            "11" -> "November"
            "12" -> "December"
            else -> ""
        }
        return "$month ${dates[2]}, ${dates[0]}"
    }
}

