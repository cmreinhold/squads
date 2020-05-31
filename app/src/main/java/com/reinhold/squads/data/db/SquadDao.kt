package com.reinhold.squads.data.db

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Dao implementation that allows us to query and operate on the Squads database.
 */
@Dao
interface SquadDao {

    @Query(value = "SELECT * FROM squads WHERE hiring_hired = 1")
    fun getHiredSquads(): LiveData<PagedList<HiredSquadEntity>>

    @Query(value = "SELECT * FROM squads WHERE hiring_hired = 0")
    fun getAvailableSquads(): LiveData<PagedList<SquadEntity>>

    @Query("SELECT * FROM squads WHERE squadId IS :squadId")
    fun getComics(squadId: String): LiveData<Array<SquadComicEntity>>

    @Query("SELECT * FROM squads WHERE squadId IS :squadId")
    fun getSquadInfo(squadId: String): LiveData<Array<SquadEntity>>

    @Query("UPDATE hiredSquads SET hired=:hired WHERE squadId = :squadId")
    fun hire(squadId: String, hired: Boolean = true)

    @Query("SELECT * FROM hiredSquads WHERE squadId IS :squadId")
    suspend fun hiredInfo(squadId: String): HiredSquadEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHired(entity: HiredSquadEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInfo(entity: SquadEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComic(entity: SquadComicEntity)

}

