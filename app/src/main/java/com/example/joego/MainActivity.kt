package com.example.joego

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.joego.ui.screens.MapScreen
import com.example.joego.ui.theme.JoeGoTheme
import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.joego.ui.screens.Navigation
import com.example.joego.ui.screens.UserMarker

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JoeGoTheme {
                Navigation()


//                val viewModel: MainViewModel = viewModel()
//                val userLocation by viewModel.userLocation.collectAsStateWithLifecycle()
//
//                val permissionLauncher = rememberLauncherForActivityResult(
//                    ActivityResultContracts.RequestMultiplePermissions()) { results ->
//                    viewModel.onPermissionResult(results.values.any { it })
//                }
//                LaunchedEffect(Unit) {
//                    if (viewModel.locationPermissionGranted.value) {viewModel.fetchLocation()
//                    } else {
//                        permissionLauncher.launch(
//                            arrayOf(
//                                Manifest.permission.ACCESS_FINE_LOCATION,
//                                Manifest.permission.ACCESS_COARSE_LOCATION
//                            )
//                        )
//                    }
//                }
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    MapScreen(modifier = Modifier.padding(innerPadding)) // just set to open map for testing, change later
//                }
            }
        }
    }
}