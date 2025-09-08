package com.example.IN2000_prosjekt.viewmodel.signs

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "oceanSigns")
data class Sign(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "description") val description: String,
    @PrimaryKey(autoGenerate = false) //already in database
    val id: Int = 0
)
