package com.example.joego.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import com.mapbox.geojson.Point
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location

@OptIn(MapboxExperimental::class)
@Composable
fun MapScreen(modifier: Modifier = Modifier) {
    MapboxMap(
       modifier = modifier.fillMaxSize(),
       mapViewportState = rememberMapViewportState {
           setCameraOptions {
               center(Point.fromLngLat(-63.5752, 44.6488))
               zoom(10.5) // tweak this if it's too zoomed out
               pitch(0.0)
               bearing(0.0)
           }
       }

           )
    {
        MapEffect(Unit) { mapView ->// this for the marker
            mapView.location.updateSettings {
                locationPuck = createDefault2DPuck(withBearing = true)//set the marker on the map
                enabled = true// to turn the marker off
                puckBearing = PuckBearing.COURSE// tells the arrow to move
                puckBearingEnabled = true// add the move marker
            }
        }// had to put it in here to stop it from crashing
    }}
