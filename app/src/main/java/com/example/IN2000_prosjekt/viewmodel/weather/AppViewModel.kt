package com.example.IN2000_prosjekt.viewmodel.weather

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.IN2000_prosjekt.model.weather.WeatherRepository
import com.example.IN2000_prosjekt.view.components.WeatherCard
import com.example.IN2000_prosjekt.view.uistate.AppUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class AppViewModel :ViewModel() {
    private val repo = WeatherRepository()

    private val _appUistate: MutableStateFlow<AppUiState> = MutableStateFlow(AppUiState.Loading)
    val appUiState: StateFlow<AppUiState> = _appUistate.asStateFlow()

    fun getAll(longitude: String, latitude: String) {
        viewModelScope.launch {
            try {
                val locationFCFetchDeferred = viewModelScope.async(Dispatchers.IO) {
                    repo.getLocation( longitude,latitude)
                }
                val locationResult = locationFCFetchDeferred.await()

                val metAlertFetchDeferred = viewModelScope.async(Dispatchers.IO) {
                    repo.getMetAlert(latitude,longitude)
                }
                val metAlertResult = metAlertFetchDeferred.await()

                _appUistate.update {
                    AppUiState.Success(
                        locationG = locationResult,
                        metAlertG = metAlertResult,
                    )
                }
            } catch (e: IOException) {
                _appUistate.update {
                    AppUiState.Error
                }
            }
        }
    }
}



@Composable
fun WeatherCardInfo(
    mapViewModel: MapViewModel,
    appViewModel: AppViewModel
) {
    val appUiState by appViewModel.appUiState.collectAsState()
    val mapCoordinates by mapViewModel.mapClickedCoordinates.collectAsState()

    // Get info based on coordinates when changed
    LaunchedEffect(mapCoordinates) {
        mapCoordinates.let {
            appViewModel.getAll(mapCoordinates.currentScreenLat.toString(), mapCoordinates.currentScreenLong.toString())
        }
    }
    when (appUiState){
        is AppUiState.Loading ->{
            Text(text = "vent")
        }
        is AppUiState.Error ->{
            Text(text = "feil")
        }
        is AppUiState.Success ->{
            WeatherCard(
                (appUiState as AppUiState.Success).locationG,
                (appUiState as AppUiState.Success).metAlertG,
                mapCoordinates
            )
        }
    }
}


