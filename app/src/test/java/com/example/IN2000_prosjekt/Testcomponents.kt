package com.example.IN2000_prosjekt

import android.graphics.Color
import androidx.compose.ui.graphics.toArgb
//import com.example.IN2000_prosjekt.view.components.fargeOppmerksomhet
import com.example.IN2000_prosjekt.view.components.getCategoryIcon
import com.example.IN2000_prosjekt.view.components.getPictureSign
import com.example.IN2000_prosjekt.viewmodel.signs.SignCategories
import junit.framework.TestCase
import org.junit.Test

class Testcomponents {
    @Test
    fun testPicture() {
        TestCase.assertEquals(R.drawable.annen_fare, getPictureSign(0))
        TestCase.assertEquals(R.drawable.annen_fare_gul, getPictureSign(1))
        TestCase.assertEquals(R.drawable.horisontal_klaring, getPictureSign(2))
        TestCase.assertEquals(R.drawable.vertikal_klaring, getPictureSign(3))
        TestCase.assertEquals(R.drawable.bevegelig_bru, getPictureSign(4))
        TestCase.assertEquals(R.drawable.kabelferge, getPictureSign(5))
        TestCase.assertEquals(R.drawable.livsfarlig_ledning, getPictureSign(6))
        TestCase.assertEquals(R.drawable.ankring_forbudt, getPictureSign(7))
        TestCase.assertEquals(R.drawable.dykking_forbudt, getPictureSign(8))
        TestCase.assertEquals(R.drawable.fartsgrense, getPictureSign(9))
        TestCase.assertEquals(R.drawable.fartsgrense_opphorer, getPictureSign(10))
        TestCase.assertEquals(R.drawable.sjotrafikk_forbudt, getPictureSign(11))
        TestCase.assertEquals(R.drawable.anropskanal, getPictureSign(12))
        TestCase.assertEquals(R.drawable.sakte_fart, getPictureSign(13))
        TestCase.assertEquals(R.drawable.dato, getPictureSign(14))
        TestCase.assertEquals(R.drawable.kabel, getPictureSign(15))
        TestCase.assertEquals(R.drawable.lav_bru, getPictureSign(16))
        TestCase.assertEquals(R.drawable.lengde_bortenfor_meter, getPictureSign(17))
        TestCase.assertEquals(R.drawable.lengde_bortenfor_nautiske_mil, getPictureSign(18))
        TestCase.assertEquals(R.drawable.lengde_fra_skiltet_meter, getPictureSign(19))
        TestCase.assertEquals(R.drawable.angir_strekning_nautiske, getPictureSign(20))
        TestCase.assertEquals(R.drawable.tid, getPictureSign(21))
        TestCase.assertEquals(R.drawable.virkeomrade, getPictureSign(22))
        TestCase.assertEquals(R.drawable.beste_punkt_for_passasje, getPictureSign(23))
        TestCase.assertEquals(R.drawable.overett, getPictureSign(24))
        TestCase.assertEquals(R.drawable.sidemarkering_babord, getPictureSign(25))
        TestCase.assertEquals(R.drawable.sidemarkering_styrbord, getPictureSign(26))
        TestCase.assertEquals(
            R.drawable.no_image_available,
            getPictureSign(27)
        ) // Eksempel på en ID som ikke er definert

    }

    @Test
    fun testCategoryIcon() {
        TestCase.assertEquals(R.drawable.forbud, getCategoryIcon(SignCategories.FORBUDSSKILT))
        TestCase.assertEquals(R.drawable.markering, getCategoryIcon(SignCategories.MARKERINGSSKILT))
        TestCase.assertEquals(R.drawable.varsel, getCategoryIcon(SignCategories.VARSELSKILT))
        TestCase.assertEquals(
            R.drawable.opplysning,
            getCategoryIcon(SignCategories.OPPLYSNINGSSKILT)
        )
        TestCase.assertEquals(R.drawable.underskilt, getCategoryIcon(SignCategories.UNDERSKILT))
    }

    @Test
    fun testWindDirectionText() {
        val testCases = listOf(
            0.0 to "NORD",
            45.0 to "NORD-ØST",
            90.0 to "ØST",
            135.0 to "SØR-ØST",
            180.0 to "SØR",
            225.0 to "SØR-VEST",
            270.0 to "VEST",
            315.0 to "NORD-VEST",
            360.0 to "NORD"
        )

        for ((verdi, expectedDirection) in testCases) {
            val direction = getWindDirectionText(verdi)
            TestCase.assertEquals(expectedDirection, direction)
        }
    }

    private fun getWindDirectionText(verdi: Double): String {
        return when (verdi) {
            in 0.0..22.5, in 337.5..360.0 -> "NORD"
            in 22.5..67.5 -> "NORD-ØST"
            in 67.5..112.5 -> "ØST"
            in 112.5..157.5 -> "SØR-ØST"
            in 157.5..202.5 -> "SØR"
            in 202.5..247.5 -> "SØR-VEST"
            in 247.5..292.5 -> "VEST"
            in 292.5..337.5 -> "NORD-VEST"
            else -> "X"
        }
    }


    private fun fargenivaaTest(farge: String): Int {
        return when (farge) {
            "Yellow" -> Color.YELLOW
            "Red" -> Color.RED
            "Green" -> Color.GREEN
            else -> Color.WHITE
        }


    }
    @Test
    fun testWindDirectionIcon() {
        val angleList = mapOf(
            0.0 to R.drawable.nord,
            45.0 to R.drawable.nord_st,
            90.0 to R.drawable._st,
            135.0 to R.drawable.s_r_st,
            180.0 to R.drawable.s_r,
            225.0 to R.drawable.s_rvest,
            270.0 to R.drawable.vest,
            315.0 to R.drawable.nordvest,
            400.0 to R.drawable.time
        )

        for ((angle, expectedDrawableId) in angleList) {
            val actualDrawableId = getDrawableForAngle(angle)

            TestCase.assertEquals(expectedDrawableId, actualDrawableId)
        }
    }

    fun getDrawableForAngle(angle: Double): Int {
        return when {
            angle in 0.0..22.5 || angle in 337.5..360.0 -> R.drawable.nord
            angle in 22.5..67.5 -> R.drawable.nord_st
            angle in 67.5..112.5 -> R.drawable._st
            angle in 112.5..157.5 -> R.drawable.s_r_st
            angle in 157.5..202.5 -> R.drawable.s_r
            angle in 202.5..247.5 -> R.drawable.s_rvest
            angle in 247.5..292.5 -> R.drawable.vest
            angle in 292.5..337.5 -> R.drawable.nordvest
            else -> R.drawable.time
        }
    }
}