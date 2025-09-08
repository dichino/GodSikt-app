package com.example.IN2000_prosjekt

import com.example.IN2000_prosjekt.view.uistate.MapUIState
import com.example.IN2000_prosjekt.viewmodel.weather.MapViewModel
import com.mapbox.maps.MapView
import io.mockk.mockk

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test

class MapviewmodelTest {
    @Test
    fun test_updateCoordinates() {
        // Opprett en instans av MapCoordinatesViewModel
        val viewModel = MapViewModel()


        val testLat = 51.5074
        val testLong = 0.1278
        viewModel.updateCoordinates(testLat, testLong)

        // Hent verdien av _mapClickedCoordinatesUIState fra viewModel
        val currentState = viewModel.mapClickedCoordinates.value

        // Sjekk om MapUIState-objektet som er produsert, er det forventede
        val expectedState = MapUIState.mapCoordinates(testLat, testLong)
        assertEquals(expectedState, currentState)
    }
    @Test
    fun `test setMapboxView`() {
        // Opprett en instans av MapViewModel
        val viewModel = MapViewModel()

        // Opprett en mock av MapView
        val mockMapView = mockk<MapView>()

        // Kall setMapboxView med mockMapView
        viewModel.setMapboxView(mockMapView)

        // Hent verdien av _mapBoxView fra viewModel
        val mapViewValue = viewModel._mapBoxView.value

        // Sjekk om verdien av _mapBoxView ikke er null
        assertNotNull(mapViewValue)
    }

}

