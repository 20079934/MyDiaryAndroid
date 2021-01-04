package com.w20079934.mydiary.activities

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import com.w20079934.mydiary.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import org.jetbrains.anko.AnkoLogger


class MainActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //i'm tired so i looked it up: https://stackoverflow.com/a/38418531/14543599
        val navigationView = findViewById<View>(R.id.navigation_view) as NavigationView
        val header = navigationView.getHeaderView(0);
        val textView = header.findViewById(R.id.diaryName) as TextView
        textView.text = "IT WORKS OMG"

    }
}