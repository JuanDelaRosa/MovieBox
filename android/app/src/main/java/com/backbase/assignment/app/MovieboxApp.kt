package com.backbase.assignment.app

import android.app.Application
import com.backbase.domain.repositories.TheMovieDBRepository
import com.backbase.domain.usecases.GetNowPlayingUseCase
import com.backbase.domain.usecases.GetPopularUseCase
import timber.log.Timber


class MovieboxApp: Application() {
    private val movieBoxRepository: TheMovieDBRepository
        get() = ServiceLocator.provideTMDBRepository(this)

    /*val saveImage: SaveImageUseCase
        get() = SaveImageUseCase(movieBoxRepository)

    val loadImage: LoadImageUseCase
        get() = LoadImageUseCase(movieBoxRepository)*/

    val getNowPlayingUseCase: GetNowPlayingUseCase
        get() = GetNowPlayingUseCase(movieBoxRepository)

    val getPopularUseCase: GetPopularUseCase
        get() = GetPopularUseCase(movieBoxRepository)

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}