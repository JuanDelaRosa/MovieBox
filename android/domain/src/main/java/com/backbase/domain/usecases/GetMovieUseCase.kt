package com.backbase.domain.usecases

import com.backbase.domain.repositories.TheMovieDBRepository

class GetMovieUseCase(private val pokeRepository: TheMovieDBRepository) {
    suspend operator fun invoke(id : Int) = pokeRepository.getMovie(id)
}