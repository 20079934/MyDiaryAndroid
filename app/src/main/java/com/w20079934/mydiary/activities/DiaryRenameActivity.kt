package com.w20079934.mydiary.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.w20079934.mydiary.R
import com.w20079934.mydiary.main.MainApp
import kotlinx.android.synthetic.main.activity_rename_diary.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast


class DiaryRenameActivity: AppCompatActivity(), AnkoLogger {
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rename_diary)
        app = application as MainApp

        toolbarAdd.title = "Rename Diary"
        setSupportActionBar(toolbarAdd)

        diaryBox.setText(app.entries.getName())

        btnUpdate.setOnClickListener()
        {
            if (diaryBox.text.isNotEmpty())
            {
                app.entries.updateName(diaryBox.text.toString())
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
            else
            {
                toast(getString(R.string.menu_emptyName))
            }
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_entry, menu)
        menu?.removeItem(R.id.removeEntry)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.cancelEntry -> {
                setResult(AppCompatActivity.RESULT_CANCELED)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
