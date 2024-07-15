package com.demate.jetweatherforecast.screens.settings

import SettingsViewModel
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.demate.jetweatherforecast.model.Unit
import com.demate.jetweatherforecast.widgets.WeatherAppBar

@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {

    var unitToggleState by remember { mutableStateOf(false) }
    val measurementUnits = listOf("Imperial (F)", "Metric (C)")
    val choiceFromDb = settingsViewModel.unitList.collectAsState().value

    val defaultChoice = if (choiceFromDb.isEmpty()) measurementUnits[0]
    else choiceFromDb[0].unit

    var choiceState by remember {
        mutableStateOf(defaultChoice)
    }

    Scaffold(topBar = {
        WeatherAppBar(
            title = "Settings",
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            false,
            navController = navController
        )
    }) {
        it.calculateBottomPadding()
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Select Measurement Unit",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(15.dp)
                )
                IconToggleButton(
                    checked = !unitToggleState, onCheckedChange = {
                        unitToggleState = !unitToggleState
                        choiceState = if (unitToggleState) {
                            "Imperial (F)"
                        } else {
                            "Metric (C)"
                        }
                    }, modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .clip(
                            shape = RectangleShape
                        )
                        .padding(5.dp)
                        .background(Color.Magenta.copy(alpha = 0.5f))
                ) {
                    Text(
                        text = if (unitToggleState) "Imperial (F)" else "Metric (C)",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(15.dp)
                    )
                }
                Button(
                    onClick = {
                        settingsViewModel.deleteAllUnits()
                        settingsViewModel.insertUnit(Unit(unit = choiceState))
                        Log.d("SettingsScreen", "Unit: $choiceState")
                    }, modifier = Modifier
                        .padding(3.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(34.dp),
                    colors = ButtonDefaults.buttonColors()
                ) {
                    Text(
                        text = "Save", modifier = Modifier.padding(5.dp),
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}