package com.example.IN2000_prosjekt.model.weather

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.serialization.gson.gson
import io.ktor.util.appendIfNameAbsent

class DataSourceLocationforecast {
    // HTTP client configuration for making requests to the weather API
    private val client = HttpClient() {
        defaultRequest {
            //base URL for weather API
            url("https://gw-uio.intark.uh-it.no/in2000/")
            // Adding API key to the request headers
            headers.appendIfNameAbsent(
                "X-Gravitee-API-Key",
                "86d1e8ef-7703-4ded-b17a-f168226135cb"
            )
        }
        install(ContentNegotiation) {
            gson()

        }
    }

    // This function fetches weather forecast data from the API based on latitude and longitude and returns a LocationforecastData object
    suspend fun fetchLocationforecast(lat:String,lon:String): LocationforecastData {
        var coordinates = "lat=$lat&lon=$lon"
        val locationResponse = client.get("weatherapi/locationforecast/2.0/complete?$coordinates")
        return locationResponse.body<LocationforecastData>()
    }
}
