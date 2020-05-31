package com.reinhold.squads.core.di

import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.reinhold.squads.data.db.SquadsDatabase
import com.reinhold.squads.data.api.SquadsMainApi
import com.reinhold.squads.domain.GetSquadsInteractor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val SQUADS_DB_FILE = "Squads.db"

/**
 * Koin main module where all dependencies are defined.
 */
fun provideSquadsModule() = module {

    single {
        Room.databaseBuilder(androidContext(), SquadsDatabase::class.java, SQUADS_DB_FILE)
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        androidContext().getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    single {
        get<SquadsDatabase>().dao()
    }

    single {
        SquadsMainApi.createSquadsApiService()
    }

    factory { ScopeDispatcher() }

    factory { GsonBuilder().create() }

    factory {
        GetSquadsInteractor(
            squadsDao = get(),
            squadsApi = get()
        )
    }
}
