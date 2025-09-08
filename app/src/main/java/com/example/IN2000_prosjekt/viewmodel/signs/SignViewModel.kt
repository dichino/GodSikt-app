package com.example.IN2000_prosjekt.viewmodel.signs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.IN2000_prosjekt.model.signs.SignDao
import com.example.IN2000_prosjekt.view.components.getPictureSign
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first

class SignViewModel(
    private val dao: SignDao
) {

    // Keep track of the category
    private val _selectedCategory = MutableLiveData<SignCategory>()
    val selectedCategory: LiveData<SignCategory> = _selectedCategory

    // Keep track of the sign
    val _selectedSign = MutableStateFlow<Sign?>(null)


    fun getPicture(id : Int): Int{
        return getPictureSign(id)
    }


    fun updateCategory(category: SignCategory) {
        _selectedCategory.value = category
    }


    fun updateSign(sign: Sign) {
        _selectedSign.value = sign
    }

    suspend fun getSignsInCategory(selectedCategory: SignCategories): List<Sign>{
        return when (selectedCategory) {
            SignCategories.MARKERINGSSKILT -> dao.getSignsInMarkeringsSkilt().first() //using .first() because the database is read-only
            SignCategories.VARSELSKILT -> dao.getSignsInVarselSkilt().first()
            SignCategories.OPPLYSNINGSSKILT -> dao.getSignsInOpplysningssSkilt().first()
            SignCategories.FORBUDSSKILT -> dao.getSignsInForbudsSkilt().first()
            SignCategories.UNDERSKILT -> dao.getSignsInUnderSkilt().first()
        }
    }

    fun getCharacters(string :String):Int {
        var numberOfCharacters = 0
        string.forEach { numberOfCharacters++ }
        return numberOfCharacters
    }
}