package com.example.IN2000_prosjekt.viewmodel.signs

import com.example.IN2000_prosjekt.view.components.getCategoryIcon

class SignCategory(
    val category : SignCategories,
    ) {
    var signsOfCategory: List<Sign> = mutableListOf()

    fun getNameString(): String {
        return when (category) {
            SignCategories.MARKERINGSSKILT -> "Markeringsskilt"
            SignCategories.VARSELSKILT -> "Varselskilt"
            SignCategories.OPPLYSNINGSSKILT -> "Opplysningsskilt"
            SignCategories.FORBUDSSKILT -> "Forbudsskilt"
            SignCategories.UNDERSKILT -> "Underskilt"
        }
    }
    
    fun getIcon(): Int {
        return getCategoryIcon(category)
    }

}
