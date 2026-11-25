package com.example.joego

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class UserLocation(val latitude: Double, val longitude: Double)

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(application)

    private val _userLocation = MutableStateFlow<UserLocation?>(null)
    val userLocation: StateFlow<UserLocation?> = _userLocation.asStateFlow()

    private val _locationPermissionGranted = MutableStateFlow(checkPermission())
    val locationPermissionGranted: StateFlow<Boolean> = _locationPermissionGranted.asStateFlow()

    private fun checkPermission(): Boolean {
        val context = getApplication<Application>()
        return ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    context, Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    fun onPermissionResult(granted: Boolean) {
        _locationPermissionGranted.value = granted
        if (granted) fetchLocation()
    }

    @SuppressLint("MissingPermission")
    fun fetchLocation() {
        if (!checkPermission()) return

        fusedLocationClient
            .getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, null)
            .addOnSuccessListener { location ->
                if (location != null) {
                    _userLocation.value = UserLocation(location.latitude, location.longitude)
                } else {
                    fusedLocationClient.lastLocation.addOnSuccessListener { last ->
                        last?.let {
                            _userLocation.value = UserLocation(it.latitude, it.longitude)
                        }
                    }
                }
            }
            .addOnFailureListener {
            }
    }

    // storing current location
    var location by mutableStateOf<Location?>(null)
        private set // other classes can read but can't modify it

    @SuppressLint("MissingPermission") // silence warning
    fun refreshLocation() {
        fusedLocationClient
            .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { result ->
                location = result
                val lat = location?.latitude;
                val long = location?.longitude;

                // sending lat and long to functionNameGoesHere()
                // functionNameGoesHere("$lat,$long")

                val latlng = "lat: ${location?.latitude}, lng: ${location?.longitude}"
                Log.i("MainViewModel", "Phone is location at $latlng.")
            }
    }
}