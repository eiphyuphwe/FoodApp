package com.example.foodreciepesapp.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.foodreciepesapp.BaseActivity
import com.example.foodreciepesapp.R
import com.example.foodreciepesapp.SessionManager
import com.google.android.material.navigation.NavigationView
import javax.inject.Inject


class MainActivity(

) : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        declare()
        init()
    }

    private fun declare() {
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout)
        navController = findNavController(R.id.nav_host_fragment)
    }

    private fun init() {
        //navController
        //navUI
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navigationView, navController)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.main, true)
                    .build()

                navController.navigate(
                    R.id.profileScreen,
                    null,
                    navOptions
                )


            }
            R.id.nav_posts -> {
                if(isValidDestination(R.id.postsScreen)) {
                    navController.navigate(R.id.postsScreen)
                }

            }

        }
        item.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true
    }

    private fun isValidDestination(destination:Int) : Boolean {
        return destination!= navController.currentDestination!!.id
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), drawerLayout);    }

}