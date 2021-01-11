package com.seo.rxdemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
        @PrimaryKey
        val uid:Int,
        @ColumnInfo(name = "first_name")
        val firstName: String,
        @ColumnInfo(name = "lastName")
        val lastName:String?
    )