package com.example.IN2000_prosjekt

import com.example.IN2000_prosjekt.model.weather.DataSourceLocationforecast
import com.example.IN2000_prosjekt.model.weather.DataSourceMetAlert
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test



class TestApi {

    @Test
    fun DataSourceLocationforecastTest() {
        val dataSource = DataSourceLocationforecast()

       runBlocking{ val locationforecastData = dataSource.fetchLocationforecast("48.394562", "-9.379260") // Random koordinater

           assertTrue(locationforecastData.toString().isNotEmpty())

       }



    }
    @Test
    fun DataSourceMetAlertTest() {
        val metAlert = DataSourceMetAlert()
        runBlocking{ val metAlertData = metAlert.fetchMetAlert("58.771760", "10.638198") // Random koordinater

            assertTrue(metAlertData.toString().isNotEmpty())

        }



    }

}