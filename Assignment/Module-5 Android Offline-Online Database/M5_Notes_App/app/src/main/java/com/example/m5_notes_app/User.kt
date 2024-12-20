package com.example.m5_notes_app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")

class User
{

    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "title")
    var title = ""

    @ColumnInfo(name = "content")
    var content = ""
}