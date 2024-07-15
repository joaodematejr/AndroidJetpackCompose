package com.demate.jetweatherforecast.screens.main

import SettingsViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.demate.jetweatherforecast.data.DataOrException
import com.demate.jetweatherforecast.model.Weather
import com.demate.jetweatherforecast.widgets.MainScaffold

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    city: String?
) {
    val curCity: String = if (city?.isBlank() == true) "Lages" else city.toString()
    val unitFromDb = settingsViewModel.unitList.collectAsState().value
    var unit by remember {
        mutableStateOf("metric")
    }
    var isImperial by remember {
        mutableStateOf(false)
    }

    if (!unitFromDb.isNotEmpty()) {
        unit = unitFromDb[0].unit.split(" ")[0].lowercase()
        isImperial = unit == "imperial"


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
                initialValue = DataOrException(loading = true)
            ) {
                value = mainViewModel.getWeatherData(city = curCity, units = unit)
            }.value

            if (weatherData.loading == true) {
                CircularProgressIndicator()
            } else if (weatherData.data != null) {
                MainScaffold(
                    weather = weatherData.data,
                    navController = navController,
                    isImperial = isImperial
                )
            } else {
                Text(text = "Error: ${weatherData.exception?.message}")
            }
        }
    }


}