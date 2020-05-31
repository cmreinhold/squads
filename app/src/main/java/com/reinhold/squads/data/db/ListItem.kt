package com.reinhold.squads.data.db

interface ListItem {
    val id: String
    fun isSame(newItem: ListItem): Boolean
}
