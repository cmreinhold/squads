package com.reinhold.squads.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hiredSquads")
data class HiredSquadEntity(
    @PrimaryKey
    val squadId: String,
    val hired: Boolean
) : ListItem {
    override val id: String get() = squadId
    override fun isSame(newItem: ListItem): Boolean =
        newItem is HiredSquadEntity && this == newItem
}

