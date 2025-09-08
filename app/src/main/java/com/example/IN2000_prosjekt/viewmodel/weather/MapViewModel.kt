package com.example.IN2000_prosjekt.viewmodel.weather


import androidx.lifecycle.ViewModel
import com.example.IN2000_prosjekt.view.uistate.MapUIState
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MapViewModel : ViewModel() {

    private val _lastUserLocation = MutableStateFlow<Point?>(null)
    var _mapBoxView = MutableStateFlow<MapView?>(null)

    private val _mapClickedCoordinatesUIState : MutableStateFlow<MapUIState.mapCoordinates> = MutableStateFlow(MapUIState.mapCoordinates())
    val mapClickedCoordinates: StateFlow<MapUIState.mapCoordinates> = _mapClickedCoordinatesUIState

    fun setLastUserLocation(point: Point){
        _lastUserLocation.value = point
        _mapClickedCoordinatesUIState.value = MapUIState.mapCoordinates(point.latitude(), point.longitude())
    }

    fun getLastUserLocation(): MutableStateFlow<Point?>{
        return _lastUserLocation
    }

    fun setMapboxView(mapBoxView: MapView){
        _mapBoxView.value = mapBoxView
    }

    fun getMapboxView(): MutableStateFlow<MapView?> {
        return _mapBoxView
    }

    fun updateCoordinates(lat: Double, long: Double) {
        _mapClickedCoordinatesUIState.value = MapUIState.mapCoordinates(lat, long)
    }
}

