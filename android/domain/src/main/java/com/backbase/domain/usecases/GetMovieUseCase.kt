package com.backbase.domain.usecases

import com.backbase.domain.repositories.TheMovieDBRepository

class GetMovieUseCase(private val theMovieDBRepository: TheMovieDBRepository) {
    suspend operator fun invoke(id : Int) = theMovieDBRepository.getMovie(id)
}