package com.reinhold.squads.data.api

/**
 * {
 *     "offset": 0, The requested offset (skipped results) of the call
 *     "limit": 20, The requested offset (skipped results) of the call
 *     "total": 30920, The total number of results available
 *     "count": 20, The total number of results returned by this call
 *     "results": [{array of objects}}] The list of entities returned by the call
 * }
 */
data class DataContainer<out T: Any>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: T? = null
)
