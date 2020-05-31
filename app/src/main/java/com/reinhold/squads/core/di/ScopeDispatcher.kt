package com.reinhold.squads.core.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ScopeDispatcher(
    val ui: CoroutineDispatcher = Dispatchers.Main,
    val io: CoroutineDispatcher = Dispatchers.IO,
    val unconfined: CoroutineDispatcher = Dispatchers.Unconfined,
    val default: CoroutineDispatcher = Dispatchers.Default
)
