package org.w20079934.mydiaryapp.fx.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate

@Parcelize
data class EntryModel (
        var id : Long = 0,
        var date: LocalDate = LocalDate.now(),
        var topic: String = "",
        var entry: String = "",
): Parcelable {
    override fun toString(): String = topic
}