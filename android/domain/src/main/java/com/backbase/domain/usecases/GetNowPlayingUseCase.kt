package com.backbase.domain.usecases

import com.backbase.domain.repositories.TheMovieDBRepository

class GetNowPlayingUseCase(private val theMovieDBRepository: TheMovieDBRepository) {
    suspend operator fun invoke() = theMovieDBRepository.getNowPlaying()
}