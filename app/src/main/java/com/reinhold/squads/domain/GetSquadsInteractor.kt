package com.reinhold.squads.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.reinhold.squads.core.logger.obtainLogger
import com.reinhold.squads.data.api.SquadsApi
import com.reinhold.squads.data.db.HiredSquadEntity
import com.reinhold.squads.data.db.SquadComicEntity
import com.reinhold.squads.data.db.SquadDao
import com.reinhold.squads.data.db.SquadEntity

class GetSquadsInteractor(
    private val squadsDao: SquadDao,
    private val squadsApi: SquadsApi
) {

    private val logger = obtainLogger("GetSquadsInteractor")

    fun getHiredSquads() = squadsDao.getHiredSquads()

    private fun getAvailableSquads() = squadsDao.getAvailableSquads()

    suspend fun getApiSquads(): LiveData<PagedList<SquadEntity>> {
        val newSquads = squadsApi.getSquads(0, 20)
        newSquads.data?.results?.forEach { characterDao ->
            val hiredEntity = HiredSquadEntity(
                squadId = characterDao.id,
                hired = false
            )
            logger.debug("Ready to insert Hired $hiredEntity")
            squadsDao.insertHired(hiredEntity)
            val dbHiredEntity = squadsDao.hiredInfo(characterDao.id)
            val infoEntity = SquadEntity(
                squadId = characterDao.id,
                description = characterDao.description ?: "",
                imageUrl = characterDao.imageUrl,
                name = characterDao.name,
                hiredState = dbHiredEntity
            )
            val comicsDto = characterDao.comics?.items?.map { comicInfo ->
                squadsApi.getComic(comicInfo.resourceURI).data?.results?.run {
                    SquadComicEntity(
                        comicId = this.id,
                        name = this.title,
                        squadId = characterDao.id,
                        imageUrl = this.thumbnailUrl
                    )
                }
            } ?: emptyList()

            logger.debug("Ready to insert comics $comicsDto")
            comicsDto.forEach { it?.let { squadsDao.insertComic(it) } }

            logger.debug("Ready to insert Info $infoEntity")
            squadsDao.insertInfo(infoEntity)
        }
        return getAvailableSquads()
    }

}
