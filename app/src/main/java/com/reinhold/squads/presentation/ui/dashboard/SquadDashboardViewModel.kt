package com.reinhold.squads.presentation.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import com.reinhold.squads.core.logger.obtainLogger
import com.reinhold.squads.domain.GetSquadsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SquadDashboardViewModel(
    private val getSquadsInteractor: GetSquadsInteractor
) : ViewModel() {

    private val logger = obtainLogger("SquadDashboardViewModel")

    fun observe() {
        val job = viewModelScope.launch(Dispatchers.IO) {
            val characters = getSquadsInteractor.getApiSquads()
            withContext(Dispatchers.Main) {
                val gson = GsonBuilder().create().toJson(characters)
                logger.debug("Captured api $gson")
            }
        }
    }
}
