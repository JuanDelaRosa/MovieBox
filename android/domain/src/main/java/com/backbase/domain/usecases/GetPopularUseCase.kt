package com.backbase.domain.usecases

import com.backbase.domain.repositories.TheMovieDBRepository

class GetPopularUseCase(private val theMovieDBRepository: TheMovieDBRepository) {
    suspend operator fun invoke(page : Int) = theMovieDBRepository.getPopular(page)
}