package com.w20079934.mydiary.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.w20079934.mydiary.R
import com.w20079934.mydiary.helpers.readImage
import com.w20079934.mydiary.helpers.readImageFromPath
import com.w20079934.mydiary.helpers.showImagePicker
import com.w20079934.mydiary.main.MainApp
import kotlinx.android.synthetic.main.activity_entry.*
import org.jetbrains.anko.toast
import org.w20079934.mydiaryapp.fx.models.EntryModel

class EntryActivity : AppCompatActivity() {
    lateinit var app : MainApp

    var entry = EntryModel()
    var edit = false
    val IMAGE_REQUEST=1

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)
        app = application as MainApp

        toolbarAdd.title = "New Entry"
        setSupportActionBar(toolbarAdd)

        textView.setText("Dear ${app.entries.getName()}")

        if(intent.hasExtra("entry_edit")) {
            edit = true
            entry = intent.extras?.getParcelable<EntryModel>("entry_edit")!!
            entryTopic.setText(entry.topic)
            entryEntry.setText(entry.entry)
            if (entry.image != "")
            {
                entryImage.setImageBitmap(readImageFromPath(this, entry.image))
                chooseImage.text = getString(R.string.button_changeImage)
            }
        }
        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }
        btnAdd.setOnClickListener()
        {
            if (entryEntry.text.isNotEmpty()) {
                if(edit) {
                    entry.topic = entryTopic.text.toString()
                    entry.entry = entryEntry.text.toString()
                    app.entries.update(entry)

                } else {
                    entry.topic = entryTopic.text.toString()
                    entry.entry = entryEntry.text.toString()
                    app.entries.create(entry.copy())
                }

                setResult(AppCompatActivity.RESULT_OK)
                finish()
            } else {
                toast(getString(R.string.menu_invalidEntry))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_entry, menu)
        if(!edit)
        {
            menu?.removeItem(R.id.removeEntry)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.cancelEntry -> {
                setResult(AppCompatActivity.RESULT_CANCELED)
                finish()
            }
            R.id.removeEntry -> {
                app.entries.remove(entry);
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    entry.image = data.data.toString()
                    entryImage.setImageBitmap(readImage(this,resultCode,data))
                    chooseImage.text = getString(R.string.button_changeImage)
                }
            }
        }
    }
}