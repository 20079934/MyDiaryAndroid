package com.w20079934.mydiary.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.w20079934.mydiary.R
import com.w20079934.mydiary.adapters.EntryAdapter
import com.w20079934.mydiary.adapters.EntryListener
import com.w20079934.mydiary.main.MainApp
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.w20079934.mydiaryapp.fx.models.EntryModel

class MainActivity : AppCompatActivity(), EntryListener  {

    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        app = application as MainApp


        toolbar.title = "Hello ${app.entries.getName()}!"
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadEntries()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> startActivityForResult<EntryActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onEntryClick(entry: EntryModel)
    {
        startActivityForResult(intentFor<EntryActivity>().putExtra("entry_edit", entry),0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadEntries()
        super.onActivityResult(requestCode, resultCode, data)
    }
    private fun loadEntries() {
        showEntries(app.entries.findAll())
    }

    fun showEntries (entries: List<EntryModel>) {
        recyclerView.adapter = EntryAdapter(entries, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}