package com.w20079934.mydiary.models

import org.w20079934.mydiaryapp.fx.models.EntryModel

interface DiaryStore {
    fun findAll(): List<EntryModel>
    fun create(placemark: EntryModel)
    fun update(placemark: EntryModel)
    fun updateName(name: String)
    fun getName(): String
}