package com.example.IN2000_prosjekt.model.weather

import com.example.IN2000_prosjekt.view.uistate.LocationInfo
import com.example.IN2000_prosjekt.view.uistate.MetAlert

class WeatherRepository {
    // Data sources for fetching MetAlert and Locationforecast data
    private val dataSourceMetAlert : DataSourceMetAlert = DataSourceMetAlert()
    private val dataSourceLocationforecast : DataSourceLocationforecast = DataSourceLocationforecast()

    // Fetches and processes MetAlert data based on coordinates
    suspend fun getMetAlert(
        lat: String,
        lon: String
    ): MetAlert {
        val metAlert = dataSourceMetAlert.fetchMetAlert( lon,lat)

        val color =
            metAlert.features.firstOrNull()?.properties?.riskMatrixColor ?: "No color available"
        val description =
            metAlert.features.firstOrNull()?.properties?.description?: "Ingen data tilgjengelig for valgt posisjon"

        // Returns a MetAlert object with the risk matrix color and description
        return MetAlert(
            riskMatrixColor = color,
            description = description
        )
    }

    // Fetches and processes weather forecast data based on coordinates
    suspend fun getLocation(
        lat: String,
        lon: String
    ): LocationInfo {
        val locationForecast = dataSourceLocationforecast.fetchLocationforecast(lat, lon)

        val temp =
            locationForecast.properties.timeseries.getOrNull(0)?.data?.instant?.details?.air_temperature?.toInt()
        val wind =
            locationForecast.properties.timeseries.firstOrNull()?.data?.instant?.details?.wind_speed
        val rain =
            locationForecast.properties.timeseries.getOrNull(0)?.data?.next_1_hours?.details?.precipitation_amount
        val rainSymbol =
            locationForecast.properties.timeseries.getOrNull(0)?.data?.next_1_hours?.summary?.symbol_code
        val fog =
            locationForecast.properties.timeseries.getOrNull(0)?.data?.instant?.details?.fog_area_fraction
        val windLocation =
            locationForecast.properties.timeseries.getOrNull(0)?.data?.instant?.details?.wind_from_direction

        // Returns a LocationInfo object with weather details
        return LocationInfo(
            temperatureL = temp ?: 0,
            fog_area_fraction = fog ?: 0.0,
            precipitation_amount = rain ?: 0.0,
            wind_speed = wind ?: 0.0,
            wind_from_direction = windLocation ?: 0.0,
            symbol_code = rainSymbol ?: "ingen data"

        )
    }

}
