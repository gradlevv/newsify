package com.gradlevv.newsify.ui

import android.animation.Animator
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.animation.AccelerateInterpolator
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.gradlevv.core.util.coreComponent
import com.gradlevv.core.util.dpf
import com.gradlevv.core.util.getSelectorDrawable
import com.gradlevv.core.util.setSystemBarsColor
import com.gradlevv.newsify.R
import com.gradlevv.newsify.di.DaggerAppComponent
import com.gradlevv.ui.utils.*


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
                        Uri.parse(applicationContext.getString(R.string.news_list_fragment)),
                        NavOptions.Builder()
                            .setPopUpTo(R.id.newsListFragment, true).build()
                    )
                    true
                }

                Menus.SOURCES.id -> {
                    navController.navigate(
                        Uri.parse(applicationContext.getString(R.string.news_sources_fragment)),
                        NavOptions.Builder()
                            .setPopUpTo(R.id.newsSourcesFragment, true).build()
                    )
                    true
                }

                Menus.SEARCH.id -> {
                    navController.navigate(
                        Uri.parse(applicationContext.getString(R.string.search_news_fragment)),
                        NavOptions.Builder()
                            .setPopUpTo(R.id.searchNewsFragment, true).build()
                    )
                    true
                }

                Menus.SETTING.id -> {
                    navController.navigate(
                        Uri.parse(applicationContext.getString(R.string.setting_fragment)),
                        NavOptions.Builder()
                            .setPopUpTo(R.id.settingFragment, true).build()
                    )
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

        initTheme()

        initBottomNavigationView()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.newsListFragment -> {
                    toggleBottomNavigationVisibility(isHide = false)
                }

                R.id.searchNewsFragment -> {
                    toggleBottomNavigationVisibility(isHide = false)
                }

                R.id.newsSourcesFragment -> {
                    toggleBottomNavigationVisibility(isHide = false)
                }

                R.id.settingFragment -> {
                    toggleBottomNavigationVisibility(isHide = false)
                }

                else -> {
                    toggleBottomNavigationVisibility(isHide = true)
                }
            }
        }

    }

    private fun initBottomNavigationView() {

        bottomNavigationView = BottomNavigationView(this).apply {
            labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_SELECTED
            setOnNavigationItemSelectedListener(navigationItemClickListener)
            setBackgroundColor(ThemeHandler.getColor(Colors.colorStatusBar))
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

        bottomNavigationView.setOnNavigationItemReselectedListener {}
    }

    private fun initTheme() {
        ThemeHandler.themeObservable.observe(this) {
            setSystemBarsColor(
                statusBarColor = ThemeHandler.getColor(Colors.colorStatusBar),
                navigationBarColor = ThemeHandler.getColor(Colors.colorStatusBar)
            )
        }
        window.decorView.setBackgroundColor(ThemeHandler.getColor(Colors.colorStatusBar))
    }

    private enum class Menus(val id: Int) {
        HOME(1),
        SOURCES(2),
        SEARCH(3),
        SETTING(4)
    }

    private fun addMenuItems() {

        bottomNavigationView.menu.add(Menu.NONE, Menus.HOME.id, Menu.NONE, "Home")
            .setChecked(true).icon = getSelectorDrawable(
            R.drawable.ic_home_fill,
            R.drawable.ic_home_stroke
        )

        bottomNavigationView.menu.add(Menu.NONE, Menus.SOURCES.id, Menu.NONE, "Sources")
            .setChecked(false).icon = getSelectorDrawable(
            R.drawable.ic_global_fill,
            R.drawable.ic_global_stroke
        )

        bottomNavigationView.menu.add(Menu.NONE, Menus.SEARCH.id, Menu.NONE, "Search")
            .setChecked(false).icon = getSelectorDrawable(
            R.drawable.ic_search_fill,
            R.drawable.ic_search_stroke
        )

        bottomNavigationView.menu.add(Menu.NONE, Menus.SETTING.id, Menu.NONE, "Setting")
            .setChecked(false).icon = getSelectorDrawable(
            R.drawable.ic_settings_fill,
            R.drawable.ic_settings_stroke
        )

        val states = arrayOf(
            intArrayOf(android.R.attr.state_checked),
            intArrayOf(-android.R.attr.state_checked)
        )
        val colors = intArrayOf(
            ThemeHandler.getColor(Colors.colorPrimary),
            ThemeHandler.getColor(Colors.colorOnBackground50)
        )

        val colorList = ColorStateList(states, colors)

        bottomNavigationView.itemIconTintList = colorList

        bottomNavigationView.itemTextColor = ThemeHandler.getColorState(Colors.colorPrimary)
        bottomNavigationView.selectedItemId = Menus.SOURCES.id
    }

    private fun toggleBottomNavigationVisibility(isHide: Boolean, animate: Boolean = true) {

        bottomNavigationContainer.clearAnimation()

        bottomNavigationContainer
            .animate()
            .setDuration(if (animate) 250 else 0)
            .translationY(if (isHide) 76.dpf() else 0f)
            .alpha(if (isHide) 0f else 1f)
            .setInterpolator(AccelerateInterpolator())
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                    bottomNavigationContainer.isGone = isHide
                    bottomNavigationView.isGone = isHide
                }

                override fun onAnimationEnd(animation: Animator?) {}
                override fun onAnimationCancel(animation: Animator?) {}
                override fun onAnimationStart(animation: Animator?) {}
            })
            .start()
    }
}