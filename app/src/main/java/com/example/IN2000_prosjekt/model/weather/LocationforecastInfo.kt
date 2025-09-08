package com.example.IN2000_prosjekt.model.weather

data class Data(
    val instant: InstantL,
    val next_12_hours: Next12Hours,
    val next_1_hours: Next1Hours,
    val next_6_hours: Next6Hours
)
data class DetailsL(
    val air_pressure_at_sea_level: Double,
    val air_temperature: Double,
    val cloud_area_fraction: Double,
    val cloud_area_fraction_high: Double,
    val cloud_area_fraction_low: Double,
    val cloud_area_fraction_medium: Double,
    val dew_point_temperature: Double,
    val fog_area_fraction: Double,
    val relative_humidity: Double,
    val ultraviolet_index_clear_sky: Double,
    val wind_from_direction: Double,
    val wind_speed: Double
)
class DetailsX

data class DetailsXX(
    val precipitation_amount: Double
)
data class DetailsXXX(
    val air_temperature_max: Double,
    val air_temperature_min: Double,
    val precipitation_amount: Double
)
data class Geometry(
    val coordinates: List<Double>,
    val type: String
)
data class InstantL(
    val details: DetailsL
)
data class LocationforecastData(
    val geometry: Geometry,
    val properties: Properties,
    val type: String
)
data class MetaL(
    val units: UnitsL,
    val updated_at: String
)
data class Next1Hours(
    val details: DetailsXX,
    val summary: Summary
)
data class Next6Hours(
    val details: DetailsXXX,
    val summary: Summary
)
data class Next12Hours(
    val details: DetailsX,
    val summary: Summary
)
data class Properties(
    val meta: MetaL,
    val timeseries: List<TimeseryL>
)
data class Summary(
    val symbol_code: String
)

data class TimeseryL(
    val `data`: Data,
    val time: String
)

data class UnitsL(
    val air_pressure_at_sea_level: String,
    val air_temperature: String,
    val air_temperature_max: String,
    val air_temperature_min: String,
    val cloud_area_fraction: String,
    val cloud_area_fraction_high: String,
    val cloud_area_fraction_low: String,
    val cloud_area_fraction_medium: String,
    val dew_point_temperature: String,
    val fog_area_fraction: String,
    val precipitation_amount: String,
    val relative_humidity: String,
    val ultraviolet_index_clear_sky: String,
    val wind_from_direction: String,
    val wind_speed: String
)
