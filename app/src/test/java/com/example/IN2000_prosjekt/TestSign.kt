package com.example.IN2000_prosjekt


import com.example.IN2000_prosjekt.viewmodel.signs.SignCategories
import com.example.IN2000_prosjekt.viewmodel.signs.SignCategory
import junit.framework.TestCase.assertEquals
import org.junit.Test

class TestSign {


    @Test
    fun testGetnameCategory() {
        val categoryMarkeringsSkilt = SignCategory(SignCategories.MARKERINGSSKILT)
        val categoryVarsleSkilt = SignCategory(SignCategories.VARSELSKILT)
        val categoryOpplyningsSkilt = SignCategory(SignCategories.OPPLYSNINGSSKILT)

        assertEquals("Markeringsskilt", categoryMarkeringsSkilt.getNameString())
        assertEquals("Varselskilt", categoryVarsleSkilt.getNameString())
        assertEquals("Opplysningsskilt", categoryOpplyningsSkilt.getNameString())
    }



}

