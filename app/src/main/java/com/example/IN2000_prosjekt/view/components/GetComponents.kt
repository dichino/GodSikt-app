package com.example.IN2000_prosjekt.view.components

import androidx.compose.runtime.Composable
import com.example.IN2000_prosjekt.R
import com.example.IN2000_prosjekt.viewmodel.signs.SignCategories


fun getPictureSign(id : Int) : Int{
     return when(id) {
         0 -> R.drawable.annen_fare
         1 -> R.drawable.annen_fare_gul
         2 -> R.drawable.horisontal_klaring
         3 -> R.drawable.vertikal_klaring
         4 -> R.drawable.bevegelig_bru
         5 -> R.drawable.kabelferge
         6 -> R.drawable.livsfarlig_ledning
         7 -> R.drawable.ankring_forbudt
         8 -> R.drawable.dykking_forbudt
         9 -> R.drawable.fartsgrense
         10 -> R.drawable.fartsgrense_opphorer
         11 -> R.drawable.sjotrafikk_forbudt
         12 -> R.drawable.anropskanal
         13 -> R.drawable.sakte_fart
         14 -> R.drawable.dato
         15 -> R.drawable.kabel
         16 -> R.drawable.lav_bru
         17 -> R.drawable.lengde_bortenfor_meter
         18 -> R.drawable.lengde_bortenfor_nautiske_mil
         19 -> R.drawable.lengde_fra_skiltet_meter
         20 -> R.drawable.angir_strekning_nautiske
         21 -> R.drawable.tid
         22 -> R.drawable.virkeomrade
         23 -> R.drawable.beste_punkt_for_passasje
         24 -> R.drawable.overett
         25 -> R.drawable.sidemarkering_babord
         26 -> R.drawable.sidemarkering_styrbord
         else -> R.drawable.no_image_available
     }
}

fun getCategoryIcon(category : SignCategories): Int{
    return when (category){
        SignCategories.FORBUDSSKILT -> R.drawable.forbud
        SignCategories.MARKERINGSSKILT -> R.drawable.markering
        SignCategories.VARSELSKILT -> R.drawable.varsel
        SignCategories.OPPLYSNINGSSKILT -> R.drawable.opplysning
        SignCategories.UNDERSKILT -> R.drawable.underskilt
    }
}


@Composable
fun windDirectionIcon(verdi: Double):Int {
    return when (verdi) {
        in 0.0..22.5, in 337.5..360.0 -> R.drawable.nord
        in 22.5..67.5 -> R.drawable.nord_st
        in 67.5..112.5 -> R.drawable._st
        in 112.5..157.5 ->R.drawable.s_r_st
        in 157.5..202.5 -> R.drawable.s_r
        in 202.5..247.5 -> R.drawable.s_rvest
        in 247.5..292.5 -> R.drawable.vest
        in 292.5..337.5 -> R.drawable.nordvest
        else -> R.drawable.time         //finne et annet bilde når det ikke kjører
    }
}


