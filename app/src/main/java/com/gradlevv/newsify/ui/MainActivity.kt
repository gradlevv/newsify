package com.gradlevv.newsify.ui

import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.gradlevv.core.util.coreComponent
import com.gradlevv.core.util.getSelectorDrawable
import com.gradlevv.newsify.R
import com.gradlevv.newsify.di.DaggerAppComponent
import com.gradlevv.ui.utils.frameLayout
import com.gradlevv.ui.utils.matchWidthCustomHeight
import com.gradlevv.ui.utils.matchWidthWrapHeight


class MainActivity : AppCompatActivity() {

    private lateinit var root: FrameLayout
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var bottomNavigationContainer: FrameLayout

    private lateinit var navController: NavController
    private lateinit var navGraph: NavGraph
    private lateinit var navHostFragment: NavHostFragment

    private val navigationItemClickListener by lazy {
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                Menus.HOME.id -> {
                    navController.navigate(
                        Uri.parse(applicationContext.getString(R.string.news_list_fragment))
                    )
                    true
                }

                Menus.FAVORITE.id -> {
                    navController.navigate(
                        Uri.parse(applicationContext.getString(R.string.favorite_news_fragment))
                    )
                    true
                }

                Menus.SEARCH.id -> {
                    navController.navigate(
                        Uri.parse(applicationContext.getString(R.string.search_news_fragment))
                    )
                    true
                }

                Menus.SETTING.id -> {
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        daggerSetUp()
        setContentView(R.layout.activity_main)
        setUpView()
    }

    private fun daggerSetUp() {
        DaggerAppComponent.factory().create(coreComponent()).inject(this)
    }

    private fun setUpView() {

        root = findViewById(R.id.root)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        val graphInflater = navHostFragment.navController.navInflater
        navGraph = graphInflater.inflate(R.navigation.navigation)
        navController = navHostFragment.navController
        navController.graph = navGraph

        initBottomNavigationView()
    }

    private fun initBottomNavigationView() {

        bottomNavigationView = BottomNavigationView(this).apply {
            labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED
            setOnNavigationItemSelectedListener(navigationItemClickListener)
        }

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

        navController.addOnDestinationChangedListener { _, destination, _ ->

        }
    }

    private enum class Menus(val id: Int) {
        HOME(1),
        FAVORITE(2),
        SEARCH(3),
        SETTING(4)
    }

    private fun addMenuItems() {

        bottomNavigationView.menu.add(Menu.NONE, Menus.HOME.id, Menu.NONE, "")
            .setChecked(true).icon = getSelectorDrawable(
            R.drawable.ic_home_fill,
            R.drawable.ic_home_stroke
        )

        bottomNavigationView.menu.add(Menu.NONE, Menus.FAVORITE.id, Menu.NONE, "")
            .setChecked(false).icon = getSelectorDrawable(
            R.drawable.ic_favorite_fill,
            R.drawable.ic_favorite_stroke
        )

        bottomNavigationView.menu.add(Menu.NONE, Menus.SEARCH.id, Menu.NONE, "")
            .setChecked(false).icon = getSelectorDrawable(
            R.drawable.ic_search_fill,
            R.drawable.ic_search_fill
        )

        bottomNavigationView.menu.add(Menu.NONE, Menus.SETTING.id, Menu.NONE, "")
            .setChecked(false).icon = getSelectorDrawable(
            R.drawable.ic_settings_fill,
            R.drawable.ic_settings_stroke
        )
    }

}