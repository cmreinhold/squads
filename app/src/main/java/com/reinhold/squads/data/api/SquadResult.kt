package com.reinhold.squads.data.api

import com.google.gson.annotations.SerializedName

/**
 * COLUMN_MARVEL_ID = "mid";
 * COLUMN_NAME = "name";
 * COLUMN_DESCRIPTION = "description";
 * COLUMN_URLDETAIL = "URLDetail";
 * COLUMN_LANDSCAPESMALL = "landscapeSmallImageUrl";
 * COLUMN_STANDARDXLARGE = "standardXLargeImageUrl";
 **/
data class SquadResult(
    @SerializedName("mid")
    val squadId: String,
    val name: String,
    val description: String
)
