package com.w20079934.mydiary.activities

import android.content.ClipData
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.w20079934.mydiary.R
import com.w20079934.mydiary.main.MainApp
import org.jetbrains.anko.AnkoLogger


class MainActivity : AppCompatActivity(), AnkoLogger {

    lateinit var app: MainApp
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app = application as MainApp

        //i'm tired so i looked it up: https://stackoverflow.com/a/38418531/14543599
        val navigationView = findViewById<View>(R.id.navigation_view) as NavigationView
        val header = navigationView.getHeaderView(0);
        val textView = header.findViewById(R.id.diaryName) as TextView
        textView.text = "Hello ${app.entries.getName()}!"

        val navController = findNavController(R.id.recyclerView)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_newentry, R.id.nav_renamediary), drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)

    }
}