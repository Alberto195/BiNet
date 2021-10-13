package com.example.binettest.presentation.core.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.binettest.R
import com.example.binettest.presentation.core.viewmodels.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = getViewModel()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(viewModel.getSession()?.sessionId == "") {
            viewModel.setSession()
        }

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                    R.id.entryListFragment,
                    R.id.addEntryFragment,
                    R.id.wholeEntryFragment
            )
        )

        val navController = findNavController(R.id.fragment_container)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_bar)
        bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(this, navController, appBarConfiguration)
    }
}