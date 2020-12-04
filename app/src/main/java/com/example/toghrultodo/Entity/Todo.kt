package com.example.toghrultodo.Entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "todo_table")
public data class Todo(
    @PrimaryKey(autoGenerate = true)
    val Id : Int,
    val Name : String,
    val Details : String,
    var Date : Calendar
) : Parcelable