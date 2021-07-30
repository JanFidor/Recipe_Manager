package com.example.recipe_manager.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "url") val type: String,
    @ColumnInfo(name = "type") val time: String,
    @ColumnInfo(name = "time") val url: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

