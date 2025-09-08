package com.example.IN2000_prosjekt.model.signs

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.IN2000_prosjekt.viewmodel.signs.Sign

@Database(
    entities = [Sign::class],
    version = 1
)

abstract class SignDatabase : RoomDatabase(){

    abstract val dao: SignDao
}