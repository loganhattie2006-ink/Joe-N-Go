package com.example.joego.ui.screens

import androidx.compose.runtime.Composable
import com.mapbox.geojson.Point
import com.mapbox.maps.MapboxExperimental
import com.example.joego.MainViewModel

import com.mapbox.maps.extension.compose.annotation.Marker



@OptIn(MapboxExperimental::class)
@Composable
fun UserMarker(viewModel: MainViewModel){// for the user marker
    val  User = viewModel.location//getting the user location
    Marker(point = Point.fromLngLat(User?.longitude ?:0.0 , User?.latitude ?: 0.0)//display the location
    )
}