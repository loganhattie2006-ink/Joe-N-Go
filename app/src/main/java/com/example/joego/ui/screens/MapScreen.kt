package com.example.joego.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mapbox.geojson.Point
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.viewport.data.FollowPuckViewportStateBearing
import com.mapbox.maps.plugin.viewport.data.FollowPuckViewportStateOptions

@OptIn(MapboxExperimental::class)
@Composable
fun MapScreen(modifier: Modifier = Modifier) {
    val mapViewportState = rememberMapViewportState {
        setCameraOptions {
            center(Point.fromLngLat(-63.5752, 44.6488)) // HRM fallback until location arrives
            zoom(10.5)
            pitch(0.0)
            bearing(0.0)
        }
    }

    // Flies the camera to the user's puck and zooms in keeps north up
    fun recenter() {
        mapViewportState.transitionToFollowPuckState(
            followPuckViewportStateOptions = FollowPuckViewportStateOptions.Builder()
                .zoom(14.0)
                .pitch(0.0)
                .bearing(FollowPuckViewportStateBearing.Constant(0.0))
                .build()
        )
    }

    Box(modifier = modifier.fillMaxSize()) {
        MapboxMap(
            modifier = Modifier.fillMaxSize(),
            mapViewportState = mapViewportState
        ) {
            MapEffect(Unit) { mapView -> // this for the marker
                mapView.location.updateSettings {
                    locationPuck = createDefault2DPuck(withBearing = true)// set the marker on the map
                    enabled = true// to turn the marker off
                    puckBearing = PuckBearing.COURSE// tells the arrow to move
                    puckBearingEnabled = true// add the move marker
                }
                recenter() // zoom to the user on launch
            }
        }

        FloatingActionButton(
            onClick = { recenter() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.MyLocation, contentDescription = "Recenter on my location")
        }
    }
}