package com.w20079934.mydiary.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.w20079934.mydiary.helpers.exists
import com.w20079934.mydiary.helpers.read
import com.w20079934.mydiary.helpers.write
import org.w20079934.mydiaryapp.fx.models.EntryModel
import java.util.*

val JSON_ENTRIES = "entries.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<EntryModel>>() {}.type
val stringType = object : TypeToken<String>() {}.type
val JSON_NAME = "diary.json"

fun generateRandomId(): Long {
    return Random().nextLong()
}

class DiaryJSONStore : DiaryStore {

    val context: Context
    var entries = mutableListOf<EntryModel>()
    var diaryName = "World"

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_ENTRIES)) {
            deserializeEntries()
        }
        if (exists(context, JSON_NAME)) {
            deserializeName()
        }
    }

    private fun serializeEntries() {
        val jsonString = gsonBuilder.toJson(entries, listType)
        write(context, JSON_ENTRIES, jsonString)
    }

    private fun deserializeEntries() {
        val jsonString = read(context, JSON_ENTRIES)
        entries = Gson().fromJson(jsonString, listType)
    }

    private fun serializeName() {
        val jsonString = gsonBuilder.toJson(diaryName, stringType)
        write(context, JSON_NAME, jsonString)
    }

    private fun deserializeName() {
        val jsonString = read(context, JSON_NAME)
        diaryName = Gson().fromJson(jsonString, stringType)
    }

    override fun findAll(): List<EntryModel> {
        return entries
    }

    override fun create(entry: EntryModel) {
        entry.id = generateRandomId()
        entries.add(entry)
        serializeEntries()
    }

    fun findOne(id: Long): EntryModel? {
        val foundEntry: EntryModel? = entries.find { p -> p.id == id }
        return foundEntry
    }

    override fun update(entry: EntryModel) {
        val foundEntry = findOne(entry.id)
        if (foundEntry != null) {
            foundEntry.entry = entry.entry
        }
        serializeEntries()
    }

    override fun updateName(name: String) {
        diaryName = name
        serializeName()
    }

    override fun getName(): String {
        return diaryName
    }
}