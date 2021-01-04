package com.w20079934.mydiary.activities

import androidx.appcompat.app.AppCompatActivity
import com.w20079934.mydiary.adapters.EntryListener
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.intentFor
import org.w20079934.mydiaryapp.fx.models.EntryModel

class DiaryListActivity: AppCompatActivity(), EntryListener, AnkoLogger {
    override fun onEntryClick(entry: EntryModel)
    {
        startActivityForResult(intentFor<EntryActivity>().putExtra("entry_edit", entry),0)
    }
}