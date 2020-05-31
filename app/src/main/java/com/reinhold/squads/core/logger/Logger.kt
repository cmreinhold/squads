package com.reinhold.squads.core.logger

import android.util.Log

fun obtainLogger(tag: String) = Logger(tag)

class Logger(private val tag: String) {

    fun debug(message: String) {
        Log.d(tag, message)
    }

    fun error(message: String, e: Throwable) {
        Log.e(tag, message, e)
    }
}
