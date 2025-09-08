package com.example.IN2000_prosjekt.view.components.mapComponents


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.extension.style.layers.properties.generated.IconAnchor
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.gestures.addOnMapClickListener

class MapboxPin(
    private val mapView: MapView,
    private val context: Context,
    drawableImageId:Int,
    private val onMapClick: (point: Point) -> Unit
    ) {

    private var pointAnnotationManager: PointAnnotationManager = mapView.annotations.createPointAnnotationManager()
    private val imageId = "location-marker-icon"

    init {
        mapView.mapboxMap.getStyle { style ->
            // Add the icon image to the current style if it's not already added
            if (!style.styleSourceExists(imageId)) {
                val bitmap = convertDrawableToBitmap(drawableImageId) ?: return@getStyle
                style.addImage(imageId, bitmap)
            }
        }
        setupClickListener()
    }

    // Sets up the map click listener to place a pin on the map
    private fun setupClickListener() {
        mapView.mapboxMap.addOnMapClickListener { point ->

            // Remove existing annotations to ensure only one pin on the map at a time
            pointAnnotationManager.deleteAll()

            // Create and add a new point annotation at the clicked location
            val pointAnnotationOptions = PointAnnotationOptions()
                .withPoint(Point.fromLngLat(point.longitude(), point.latitude()))
                .withIconImage(imageId)
                .withIconSize(1.5)
                .withIconAnchor(IconAnchor.BOTTOM)
                .withIconOffset(listOf(0.0, 4.0)) // Offset the icon 4 pixels down


            pointAnnotationManager.create(pointAnnotationOptions)
            mapView.mapboxMap.setCamera(CameraOptions.Builder().center(point).build())

            onMapClick(point);
            true // Indicate that the click event has been handled
        }
    }

    // Converts a drawable resource to a Bitmap
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun convertDrawableToBitmap(drawableId: Int): Bitmap? {
        val drawable = context.resources.getDrawable(drawableId, context.theme)
        return if (drawable is BitmapDrawable) {
            drawable.bitmap
        } else {
            val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            drawable.draw(canvas)
            bitmap
        }
    }
}
