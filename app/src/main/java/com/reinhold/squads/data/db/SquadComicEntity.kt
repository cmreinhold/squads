package com.reinhold.squads.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comics")
data class SquadComicEntity(
    @PrimaryKey
    val comicId: String,
    val name: String,
    val imageUrl: String,
    val squadId: String
) : ListItem {
    override val id: String get() = comicId
    override fun isSame(newItem: ListItem): Boolean =
        newItem is SquadComicEntity && this == newItem
}

