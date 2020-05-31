package com.reinhold.squads.data.api

/**
 * {
 *    "code": 200, the http status code of the success or error.
 *    "status": "Ok", a description of success or error.
 *    "etag": "f0fbae65eb2f8f28bdeea0a29be8749a4e67acb3", A digest value of the content (only on success)
 *    "data": { // only on success
 *      "offset": 0,
 *      "limit": 20,
 *      "total": 30920,
 *      "count": 20,
 *      "results": [{array of objects}}]
 *    }
 * }
 */
data class DataWrapper<out T: Any>(
    val code: Int,
    val copyright: String? = null,
    val status: String? = null,
    val etag: String? = null,
    val data: DataContainer<T>? = null
)
