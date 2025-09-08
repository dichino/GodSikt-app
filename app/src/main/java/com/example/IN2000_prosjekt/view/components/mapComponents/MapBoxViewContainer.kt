package com.example.IN2000_prosjekt.view.components.mapComponents

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.extension.style.atmosphere.generated.atmosphere
import com.mapbox.maps.extension.style.layers.generated.skyLayer
import com.mapbox.maps.extension.style.layers.properties.generated.ProjectionName
import com.mapbox.maps.extension.style.layers.properties.generated.SkyType
import com.mapbox.maps.extension.style.projection.generated.projection
import com.mapbox.maps.extension.style.sources.generated.rasterDemSource
import com.mapbox.maps.extension.style.style
import com.mapbox.maps.extension.style.terrain.generated.terrain

// Container class for managing MapView instance and state
class MapViewContainer(initialMapView: MapView? = null, private val onMapReady: () -> Unit = {}) {
    private var _mapView: MapView? = initialMapView

    var mapView: MapView?
        get() = _mapView
        private set(value) {
            _mapView = value
            isMapViewReady = value != null
            if (isMapViewReady) {
                onMapReady()
            }
        }

    var isMapViewReady by mutableStateOf(initialMapView != null)
        private set

    fun setMapViewInstance(mapView: MapView?) {
        this.mapView = mapView
    }

    fun setCameraLocation(mapboxmap: MapboxMap,point: Point?){
        mapboxmap.setCamera(CameraOptions.Builder().center(point).build())
    }
}

// Composable function to create a Mapbox map component
@Composable
fun MapboxMapComponent(
    modifier: Modifier = Modifier.fillMaxSize(),
    mapViewContainer: MapViewContainer,
    initialCameraOptions: CameraOptions? = null,
    onMapReady: () -> Unit = {}
) {
    val SOURCE = "TERRAIN_SOURCE"
    val SKY_LAYER = "sky"
    val TERRAIN_URL_TILE_RESOURCE = "mapbox://mapbox.mapbox-terrain-dem-v1"
    val TERRAIN_EXEGERATION = 1.4

    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            MapView(ctx).also { mapView ->
                mapViewContainer.setMapViewInstance(mapView)

                // Load a custom style for the map
                mapView.mapboxMap.loadStyle(style(style = "mapbox://styles/mapbox/navigation-night-v1") {
                    +rasterDemSource(SOURCE) {
                        url(TERRAIN_URL_TILE_RESOURCE)
                        tileSize(514)
                    }
                    +terrain(SOURCE) {
                        exaggeration(TERRAIN_EXEGERATION)
                    }
                    +skyLayer(SKY_LAYER){
                        skyType(SkyType.ATMOSPHERE)
                        skyAtmosphereSun(listOf(0.0, 90.2))
                    }
                    +atmosphere { }
                    +projection(ProjectionName.GLOBE)
                })
                // Apply initial camera options
                initialCameraOptions?.let { cameraOptions ->
                    mapView.mapboxMap.setCamera(cameraOptions)
                }

                // This block is executed when the map is ready
                onMapReady()
            }
        }
    )
}

