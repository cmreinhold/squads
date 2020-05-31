package com.reinhold.squads.data.api

/**
 * - id	int	The unique ID of the comic resource.
 * - digitalId	int	The ID of the digital comic representation of this comic. Will be 0 if the comic is not available digitally.
 * - title	string	The canonical title of the comic.
 * - issueNumber	int	The number of the issue in the series (will generally be 0 for collection formats).
 * - variantDescription	string	If the issue is a variant (e.g. an alternate cover, second printing, or director's cut), a text description of the variant.
 * - description	string	The preferred description of the comic.
 * - modified	Date	The date the resource was most recently modified.
 * - isbn	string	The ISBN for the comic (generally only populated for collection formats).
 * - upc	string	The UPC barcode number for the comic (generally only populated for periodical formats).
 * - diamondCode	string	The Diamond code for the comic.
 * - ean	string	The EAN barcode for the comic.
 * - issn	string	The ISSN barcode for the comic.
 * - format	string	The publication format of the comic e.g. comic, hardcover, trade paperback.
 * - pageCount	int	The number of story pages in the comic.
 */
class ResourceDto(
    val id: String,
    val digitalId: String,
    val title: String,
    val issueNumber: Int,
    val pageCount: Int,
    val description: String,
    val thumbnail: ImageDto
) {
    val thumbnailUrl: String
        get() = "${thumbnail.path}.${thumbnail.extension}"
}
