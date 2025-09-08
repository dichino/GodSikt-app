package com.example.IN2000_prosjekt.model.signs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.IN2000_prosjekt.viewmodel.signs.Sign
import kotlinx.coroutines.flow.Flow


@Dao
interface SignDao {

    @Upsert
    suspend fun upsertSign(sign: Sign)

    @Delete
    suspend fun deleteSign(sign: Sign)

    @Query("SELECT * FROM oceanSigns ORDER BY category ASC")
    fun getSignsOrderedByCategory(): Flow<List<Sign>>

    @Query("SELECT * FROM oceanSigns ORDER BY name ASC")
    fun getSignsOrderedByName(): Flow<List<Sign>>

    @Query("SELECT * FROM oceanSigns ORDER BY description ASC")
    fun getSignsOrderedByDescription(): Flow<List<Sign>>

    @Query("SELECT * FROM oceanSigns WHERE category == 'varselskilt' ORDER BY name ASC")
    fun getSignsInVarselSkilt(): Flow<List<Sign>>

    @Query("SELECT * FROM oceanSigns WHERE category == 'forbudsskilt' ORDER BY name ASC")
    fun getSignsInForbudsSkilt(): Flow<List<Sign>>

    @Query("SELECT * FROM oceanSigns WHERE category == 'opplysningsskilt' ORDER BY name ASC")
    fun getSignsInOpplysningssSkilt(): Flow<List<Sign>>

    @Query("SELECT * FROM oceanSigns WHERE category == 'markeringsskilt' ORDER BY name ASC")
    fun getSignsInMarkeringsSkilt(): Flow<List<Sign>>

    @Query("SELECT * FROM oceanSigns WHERE category == 'underskilt' ORDER BY name ASC")
    fun getSignsInUnderSkilt(): Flow<List<Sign>>
}