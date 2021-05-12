package com.backbase.assignment.app

import android.app.Application
import com.backbase.domain.repositories.TheMovieDBRepository
import com.backbase.domain.usecases.GetMovieUseCase
import com.backbase.domain.usecases.GetNowPlayingUseCase
import com.backbase.domain.usecases.GetPopularUseCase
import timber.log.Timber

class MovieBoxApp: Application() {
    private val movieBoxRepository: TheMovieDBRepository
        get() = ServiceLocator.providePokeRepository()

    val getMovieUseCase: GetMovieUseCase
        get() = GetMovieUseCase(movieBoxRepository)

    val getNowPlayingUseCase: GetNowPlayingUseCase
        get() = GetNowPlayingUseCase(movieBoxRepository)

    val getPopularUseCase: GetPopularUseCase
        get() = GetPopularUseCase(movieBoxRepository)

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}