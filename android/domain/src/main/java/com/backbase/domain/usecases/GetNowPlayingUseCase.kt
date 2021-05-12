package com.backbase.domain.usecases

import com.backbase.domain.repositories.TheMovieDBRepository

class GetNowPlayingUseCase(private val pokeRepository: TheMovieDBRepository) {
    suspend operator fun invoke() = pokeRepository.getNowPlaying()
}