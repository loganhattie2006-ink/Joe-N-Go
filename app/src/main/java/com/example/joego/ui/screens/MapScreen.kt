package com.example.joego.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mapbox.geojson.Point
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState

@OptIn(MapboxExperimental::class)
@Composable
fun MapScreen(modifier: Modifier = Modifier) {
    MapboxMap(
       modifier = modifier.fillMaxSize(),
       mapViewportState = rememberMapViewportState {
           setCameraOptions {
               center(Point.fromLngLat(-63.5752, 44.6488))
               zoom(10.5) // tweak this if its too zoomed out
               pitch(0.0)
               bearing(0.0)
           }
       }
   )
}
