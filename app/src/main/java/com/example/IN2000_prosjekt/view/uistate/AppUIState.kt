package com.example.IN2000_prosjekt.view.uistate

data class LocationInfo(
    val temperatureL: Int,
    val fog_area_fraction: Double,
    val precipitation_amount: Double,
    val wind_speed: Double,
    val wind_from_direction :Double,
    val symbol_code: String
)
data class MetAlert(
    val riskMatrixColor: String,
    val description: String,
    )

sealed interface AppUiState {
    data class Success(
        val locationG: LocationInfo,
        val metAlertG: MetAlert
    ) : AppUiState
    object Error : AppUiState
    object Loading : AppUiState
}
