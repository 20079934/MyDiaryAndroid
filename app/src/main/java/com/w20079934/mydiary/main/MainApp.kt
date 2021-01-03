package com.w20079934.mydiary.main

import android.app.Application
import com.w20079934.mydiary.models.DiaryJSONStore
import com.w20079934.mydiary.models.DiaryStore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp : Application(), AnkoLogger {
    lateinit var entries : DiaryStore

    override fun onCreate() {
        super.onCreate()
        entries = DiaryJSONStore(applicationContext)
        info("Diary started")
    }
}