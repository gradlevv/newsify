package com.gradlevv.newsify.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gradlevv.newsify.R
import com.gradlevv.ui.utils.frameLayout
import com.gradlevv.ui.utils.matchWidthCustomHeight
import com.gradlevv.ui.utils.matchWidthWrapHeight
import java.lang.IllegalArgumentException


class MainActivity : AppCompatActivity() {

    private lateinit var root: FrameLayout
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var bottomNavigationContainer: FrameLayout

    private lateinit var navController: NavController
    private lateinit var navGraph: NavGraph
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        setUpView()
    }

    private fun setUpView() {

        root = findViewById(R.id.root)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        val graphInflater = navHostFragment.navController.navInflater
        navGraph = graphInflater.inflate(R.navigation.navigation)
        navController = navHostFragment.navController

        initBottomNavigationView()
    }

    private fun initBottomNavigationView() {

        bottomNavigationView = BottomNavigationView(this)

        bottomNavigationContainer = frameLayout {
            addView(bottomNavigationView, matchWidthCustomHeight(72) {
                gravity = Gravity.BOTTOM
            })
        }

        addMenuItems()

        with(root) {
            addView(bottomNavigationContainer, matchWidthWrapHeight {
                gravity = Gravity.BOTTOM
            })
        }
    }

    private fun addMenuItems() {

    }

}