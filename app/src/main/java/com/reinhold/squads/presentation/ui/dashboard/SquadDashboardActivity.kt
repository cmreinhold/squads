package com.reinhold.squads.presentation.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reinhold.squads.R
import kotlinx.android.synthetic.main.activity_scrolling.*
import org.koin.core.KoinComponent
import org.koin.core.get

class SquadDashboardActivity : AppCompatActivity(), KoinComponent {

    private val viewModel = SquadDashboardViewModel(
        getSquadsInteractor = get()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.toolbar_squad_title)

        viewModel.observe()
    }

}
