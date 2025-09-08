package com.example.IN2000_prosjekt.view.components.mapComponents


import com.mapbox.maps.MapboxMap
import com.mapbox.maps.extension.style.layers.addLayerAbove
import com.mapbox.maps.extension.style.layers.generated.rasterLayer
import com.mapbox.maps.extension.style.sources.addSource
import com.mapbox.maps.extension.style.sources.generated.rasterSource

/**
 * Adds a raster layer for Dybdedata to the provided Mapbox map.
 * Ensures the source is added before attempting to add the layer.
 *
 * @param mapboxMap The MapboxMap instance where the raster layer will be added.
 */
fun addDybdedataLayer(mapboxMap: MapboxMap) {
    val sourceId = "dybdedata-source"
    val layerId = "dybdedata-layer"
    val tileUrl = "https://wms.geonorge.no/skwms1/wms.dybdedata2?bbox={bbox-epsg-3857}&format=image/png&service=WMS&version=1.1.1&request=GetMap&srs=EPSG:3857&transparent=true&width=512&height=512&layers=Dybdedata2,Dybdekontur,grunne&styles=default"


    // Obtain the current style from the MapboxMap instance
    mapboxMap.getStyle { style ->

        if (!style.styleSourceExists(sourceId)) {
            // Add the raster source
            style.addSource(
                rasterSource(sourceId) {
                    tileSize(512)
                    tileSet("tileset", listOf(tileUrl)) {}
                    minzoom(6)
                    maxzoom(17)
                }
            )
        }

        // Ensure the layer does not already exist before adding it
        if (!style.styleLayerExists(layerId)) {
            // Add the raster layer using the source
            style.addLayerAbove(
                rasterLayer(layerId, sourceId) {
                    rasterOpacity(0.8)
                },
                // Attempt to add above the last symbol layer, if exists; adjust this based on your map style
                style.styleLayers.lastOrNull { it.type == "symbol" }?.id ?: "background"
            )
        }
    }
}

