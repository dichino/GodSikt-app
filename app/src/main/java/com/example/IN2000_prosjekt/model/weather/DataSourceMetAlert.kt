package com.example.IN2000_prosjekt.model.weather

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.serialization.gson.gson
import io.ktor.util.appendIfNameAbsent

class DataSourceMetAlert() {
    // HTTP client configuration for making requests to the weather API
    private val client = HttpClient() {
        defaultRequest {
            //base URL for weather API
            url("https://gw-uio.intark.uh-it.no/in2000/weatherapi/")
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

    // This function fetches weather Metlartdata from the API based on latitude and longitude and returns a Metalert object
    suspend fun fetchMetAlert(lat :String, lon: String): MetAlertData {
        val coordinates = "lat=$lat&lon=$lon"
        val metAlertResponse = client.get("metalerts/2.0/all.json?$coordinates")
        return metAlertResponse.body()
    }
}