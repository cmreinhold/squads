package com.reinhold.squads.data.api

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import kotlin.experimental.and
import kotlin.experimental.or

fun String.toSha1(): String = sha1(this)
fun String.toMD5(): String = generateHash(this)

fun ByteArray.toHex(): String {
    return joinToString("") { "%02x".format(it) }
}

private fun sha1(input: String) = hashString("SHA-1", input)
private fun md5(input: String) = hashString("MD5", input)

private val HEX_CHARS = "0123456789ABCDEF".toCharArray()

private fun hashString(type: String, input: String): String {
    val bytes = MessageDigest
        .getInstance(type)
        .digest(input.toByteArray())
    return printHexBinary(bytes).toUpperCase(Locale.UK)
}


fun printHexBinary(data: ByteArray): String {
    val builder = StringBuilder(data.size * 2)
    data.forEach { b ->
        val i = b.toInt()
        builder.append(HEX_CHARS[i shr 4 and 0xF])
        builder.append(HEX_CHARS[i and 0xF])
    }
    return builder.toString()
}

@Throws(NoSuchAlgorithmException::class)
fun generateHash(value: String) = try {
    val md5Encoder = MessageDigest.getInstance("MD5")
    val md5Bytes = md5Encoder.digest(value.toByteArray())
    val md5 = java.lang.StringBuilder()
    for (i in md5Bytes.indices) {
        md5.append(
            Integer
                .toHexString(md5Bytes[i].toInt() and 0xFF or 0x100)
                .substring(1, 3)
        )
    }
    md5.toString()
} catch (e: NoSuchAlgorithmException) {
    throw NoSuchAlgorithmException("Cannot generate the api key", e)
}
