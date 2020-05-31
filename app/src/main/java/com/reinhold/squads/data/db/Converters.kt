package com.reinhold.squads.data.db

import androidx.room.TypeConverter
import com.reinhold.squads.data.api.CharacterDto
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun characterDtoToEntity(character: CharacterDto?): SquadEntity? = character?.run {
        SquadEntity(id, name, description ?: "", imageUrl)
    }
}
