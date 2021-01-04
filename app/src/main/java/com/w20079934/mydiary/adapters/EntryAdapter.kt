package com.w20079934.mydiary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.w20079934.mydiary.R
import com.w20079934.mydiary.helpers.readImageFromPath
import kotlinx.android.synthetic.main.activity_entry.view.*
import kotlinx.android.synthetic.main.card_diary.view.*
import org.w20079934.mydiaryapp.fx.models.EntryModel

interface EntryListener {
    fun onEntryClick(entry: EntryModel)
}

class EntryAdapter constructor(private var entries: List<EntryModel>, private val listener : EntryListener) : RecyclerView.Adapter<EntryAdapter.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_diary,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val placemark = entries[holder.adapterPosition]
        holder.bind(placemark, listener)
    }

    override fun getItemCount(): Int = entries.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(entry: EntryModel, listener: EntryListener) {
            itemView.dayNumber.text = "${entry.date.get("day")}-${entry.date.get("month")}-${entry.date.get("year")}"
            itemView.dayTopic.text = entry.topic
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context,entry.image))
            itemView.setOnClickListener {listener.onEntryClick(entry)}
        }
    }
}