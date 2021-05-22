package com.backbase.assignment.test

import org.junit.Assert.assertTrue
import org.junit.Test

class ValidationsTest {

    @Test
    fun movieReleaseDate() {
        val releaseDate = "12-03-1995"
        assertTrue(Validations.MovieReleaseDate(releaseDate))
    }

    @Test
    fun movieReleaseDateFalse() {
        val releaseDate = "12/03/1995"
        assertTrue(Validations.MovieReleaseDateFalse(releaseDate))
    }

    @Test
    fun movieTitle() {
        val title = "An amazing new movie about dogs"
        assertTrue(Validations.MovieTitle(title))
    }

    @Test
    fun movieRuntime() {
        val runtime = 100
        assertTrue(Validations.MovieRuntime(runtime))
    }
}