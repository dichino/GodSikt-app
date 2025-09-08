
package com.example.IN2000_prosjekt.view.components.mapComponents

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.mapbox.geojson.Point
import com.mapbox.maps.ImageHolder
import com.mapbox.maps.MapView
import com.mapbox.maps.extension.style.expressions.dsl.generated.interpolate
import com.mapbox.maps.plugin.LocationPuck2D
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorPositionChangedListener
import com.mapbox.maps.plugin.locationcomponent.location


/**
 * Initializes and customizes the user's location component on the map.
 *
 * @param mapView The MapView to add the location component to.
 * @param context The context, used for accessing resources.
 */

class MapboxUserLocation(){

    var onLastLocation: ((Point) -> Unit)? = null
    var mapBoxMapView: MapView? = null

    public fun initUserLocationComponent(mapView: MapView) {
        mapBoxMapView = mapView

        // Check if location permissions are granted
        if (ContextCompat.checkSelfPermission(mapView.context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Consider invoking permission request logic here or notify the user
            return
        }

        val locationComponent = mapView.location

        val locationPuck = LocationPuck2D(
            topImage = ImageHolder.from(com.mapbox.maps.plugin.locationcomponent.R.drawable.mapbox_user_icon),
            bearingImage = ImageHolder.from(com.mapbox.maps.plugin.locationcomponent.R.drawable.mapbox_user_bearing_icon),
            shadowImage = ImageHolder.from(com.mapbox.maps.plugin.locationcomponent.R.drawable.mapbox_user_stroke_icon),
            scaleExpression = interpolate {
                linear()
                zoom()
                stop {
                    literal(0.0)
                    literal(0.6)
                }
                stop {
                    literal(20.0)
                    literal(1.0)
                }
            }.toJson()
        )

        // Apply the custom location puck to the location component
        locationComponent.updateSettings {
            this.enabled = true
            this.locationPuck = locationPuck
            this.puckBearing = PuckBearing.COURSE
        }

        // Add listener for position changes
        locationComponent.addOnIndicatorPositionChangedListener(OnIndicatorPositionChangedListener {
            // Handle the new position (e.g., update UI or map camera)
            onLastLocation?.invoke(it)
        })
    }
}


