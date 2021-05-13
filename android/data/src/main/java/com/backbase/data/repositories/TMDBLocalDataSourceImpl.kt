package com.backbase.data.repositories

import com.backbase.data.mappers.TMDBLocalMapper
import com.backbase.domain.entities.DetailDB
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TMDBLocalDataSourceImpl(
    //private val dao: TMDBDAO,
    private val dispatcher: CoroutineDispatcher,
    private val mapper : TMDBLocalMapper
) : TMDBLocalDataSource{

    override suspend fun saveImage(detail: DetailDB) {
       // dao.saveImage(mapper.toImage(image))
    }

    override suspend fun getImage(id: Int): DetailDB =
        withContext(dispatcher) {
          //  val image = dao.getImage(id)
            return@withContext mapper.toImageDB(null)
        }
}