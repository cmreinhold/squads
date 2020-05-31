package com.reinhold.squads.data.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "squads")
data class SquadEntity(
    @PrimaryKey
    val squadId: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    @Embedded(prefix = "hiring_")
    val hiredState: HiredSquadEntity
) : ListItem {
    override val id: String get() = squadId
    override fun isSame(newItem: ListItem): Boolean =
        newItem is SquadEntity && this == newItem
}

