package com.reinhold.squads.data.api

data class ResourceListDto(
    val available: Int,
    val returned: Int,
    val collectionUri: String,
    val items: ArrayList<ResourceReferenceDto>
)
