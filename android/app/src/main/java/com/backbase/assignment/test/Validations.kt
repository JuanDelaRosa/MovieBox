package com.backbase.assignment.test

import com.backbase.domain.entities.Movie

object Validations {

    fun MovieReleaseDate(releaseDate : String) : Boolean{
        val m = Movie(0,"",releaseDate,"Avatar",10.0,"",100, emptyList())
        return (m.ReleaseDateFix()!="-")
    }
    fun MovieReleaseDateFalse(releaseDate : String) : Boolean{
        val m = Movie(0,"",releaseDate,"Avatar",10.0,"",100, emptyList())
        return (m.ReleaseDateFix()=="-")
    }
    fun MovieTitle(title : String) : Boolean{
        val m = Movie(0,"","12-03-1995",title,10.0,"",100, emptyList())
        val fixedTitle = m.TitleFix()

        return if(m.title.length>28)
            (fixedTitle.contains("...") && fixedTitle.length==28)
        else
            (m.title.length<=28 && !fixedTitle.contains("...") )
    }
    fun MovieRuntime(runtime : Int) : Boolean{
        val m = Movie(0,"","12-03-1995","Avatar",10.0,"",runtime, emptyList())
        val fixedRuntime = m.DurationFix()
        return if(m.runtime>0){
            (fixedRuntime.contains("h") && fixedRuntime.contains("m"))
        }else (m.DurationFix()=="-")
    }
}