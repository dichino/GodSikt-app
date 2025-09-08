package com.example.IN2000_prosjekt


import com.example.IN2000_prosjekt.model.weather.WeatherRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class TestRepository {

    val weatherRepository: WeatherRepository = WeatherRepository()

    @Test
    fun testMetalert(){
        val respons = runBlocking { weatherRepository.getMetAlert("54.968155", "19.749559") }
        assertTrue(respons.description.isNotEmpty())
        assertTrue(respons.riskMatrixColor.isNotEmpty())
    }
  @Test
  fun testgetLocation() {
      val respons = runBlocking {
           weatherRepository.getLocation("48.394562", "-9.379260")

          }
      assertNotNull(respons.temperatureL)
      assertNotNull(respons.fog_area_fraction)
      assertNotNull(respons.precipitation_amount)
      assertNotNull(respons.wind_speed)
      assertNotNull(respons.wind_from_direction)
      assertNotNull(respons.symbol_code)

  }

  }











