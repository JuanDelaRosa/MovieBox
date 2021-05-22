package com.backbase.assignment.app

import android.app.Application
import com.backbase.domain.repositories.TheMovieDBRepository
import com.backbase.domain.usecases.GetFromLocalDBUseCase
import com.backbase.domain.usecases.GetNowPlayingUseCase
import com.backbase.domain.usecases.GetPopularUseCase


class MovieboxApp: Application() {
    private val movieBoxRepository: TheMovieDBRepository
        get() = ServiceLocator.provideTMDBRepository(this)

    val getNowPlayingUseCase: GetNowPlayingUseCase
        get() = GetNowPlayingUseCase(movieBoxRepository)

    val getPopularUseCase: GetPopularUseCase
        get() = GetPopularUseCase(movieBoxRepository)

    val getFromLocalDB: GetFromLocalDBUseCase
        get() = GetFromLocalDBUseCase(movieBoxRepository)

}