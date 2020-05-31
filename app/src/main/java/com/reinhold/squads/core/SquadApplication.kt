package com.reinhold.squads.core

import android.app.Application
import com.reinhold.squads.core.di.provideSquadsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SquadApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidContext(this@SquadApplication)
            modules(listOf(provideSquadsModule()))
        }
    }

}
