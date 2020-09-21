package com.servicetitan.mviexample.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.servicetitan.mviexample.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NavHostFragment.create(R.navigation.nav_graph).apply {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, this)
                .setPrimaryNavigationFragment(this)
                .commit()
        }
    }
}

