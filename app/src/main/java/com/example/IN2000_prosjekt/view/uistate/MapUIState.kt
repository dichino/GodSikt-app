package com.example.IN2000_prosjekt.view.uistate

sealed interface MapUIState{
    data class mapCoordinates(
        val currentScreenLat: Double = 10.785439,
        val currentScreenLong: Double = 59.783447,
    )
}