package com.backbase.domain.usecases

import com.backbase.domain.repositories.TheMovieDBRepository

class GetFromLocalDBUseCase(private val theMovieDBRepository: TheMovieDBRepository) {
    suspend operator fun invoke(type : Int) = theMovieDBRepository.getFromLocalDB(type)
}