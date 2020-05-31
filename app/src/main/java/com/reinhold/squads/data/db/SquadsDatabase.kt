package com.reinhold.squads.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [SquadEntity::class, SquadComicEntity::class, HiredSquadEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class SquadsDatabase : RoomDatabase() {
    abstract fun dao(): SquadDao
}
