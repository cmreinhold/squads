package com.reinhold.squads.data.api

/**
 * - id	        int	    The unique ID of the character resource.
 * - name	    string	The name of the character.
 * - description    string	A short bio or description of the character.
 * - modified	Date	The date the resource was most recently modified.
 * - resourceURI	string	The canonical URL identifier for this resource.
 * - urls	Array[Url]	A set of public web site URLs for the resource.
 * - thumbnail	Image	The representative image for this character.
 * - comics	ResourceList	A resource list containing comics which feature this character.
 * - stories ResourceList	A resource list of stories in which this character appears.
 * - events	ResourceList	A resource list of events in which this character appears.
 * - series	ResourceList	A resource list of series in which this character appears.
 */
data class CharacterDto(
    val id: String,
    val name: String,
    val description: String?,
    val thumbnail: ImageDto,
    val comics: ResourceListDto?
) {
    val imageUrl: String
        get() = "${thumbnail.path}.${thumbnail.extension}"
}
