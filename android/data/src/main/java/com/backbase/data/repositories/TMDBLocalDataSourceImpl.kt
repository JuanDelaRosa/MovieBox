package com.backbase.data.repositories

import com.backbase.data.db.entities.TMDBDAO
import com.backbase.data.mappers.TMDBLocalMapper
import com.backbase.domain.entities.DetailDB
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TMDBLocalDataSourceImpl(
    private val dao: TMDBDAO,
    private val dispatcher: CoroutineDispatcher,
    private val mapper : TMDBLocalMapper
) : TMDBLocalDataSource{

    override suspend fun saveDetail(detail: DetailDB) {
       dao.saveDetail(mapper.toLocalDetail(detail))
    }

    override suspend fun getDetail(id: Int): DetailDB =
        withContext(dispatcher) {
            val detail = dao.getDetail(id)
            return@withContext mapper.toDetailDB(detail)
        }
}