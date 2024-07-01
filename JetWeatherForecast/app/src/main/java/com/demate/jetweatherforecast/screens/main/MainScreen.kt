package com.demate.jetweatherforecast.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.demate.jetweatherforecast.R
import com.demate.jetweatherforecast.data.DataOrException
import com.demate.jetweatherforecast.model.Weather
import com.demate.jetweatherforecast.model.WeatherItem
import com.demate.jetweatherforecast.utils.formatDate
import com.demate.jetweatherforecast.utils.formatDateTime
import com.demate.jetweatherforecast.utils.formatDecimals
import com.demate.jetweatherforecast.widgets.WeatherAppBar

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
            initialValue = DataOrException(loading = true)
        ) {
            value = mainViewModel.getWeatherData(city = "London")
        }.value

        if (weatherData.loading == true) {
            CircularProgressIndicator()
        } else if (weatherData.data != null) {
            MainScaffold(weather = weatherData.data, navController = navController)
        } else {
            Text(text = "Error: ${weatherData.exception?.message}")
        }
    }
}

@Composable
fun MainScaffold(weather: Weather, navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = weather.city.name + weather.city.country,
            navController = navController,
            //icon = Icons.Default.ArrowBackIosNew
        ) {
            navController.popBackStack()
        }
    }) {
        it.calculateBottomPadding()
        MainContent(data = weather)
    }
}

@Composable
fun MainContent(data: Weather) {
    //https://openweathermap.org/img/w/04d.png
    val imageUrl = "https://openweathermap.org/img/w/${data.list.first().weather.first().icon}.png"
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatDate(data.list.first().dt),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
            modifier = Modifier.padding(6.dp)
        )

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xDFB9A151)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Image
                WeatherStateImage(imageUrl = imageUrl)
                Text(
                    text = formatDecimals(data.list.first().temp.day) + "°",
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = data.list.first().weather.first().main,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
        HumidityWindPressureRow(weather = data.list.first())
        HorizontalDivider()
        SunsetSunriseRow(weather = data.list.first())

    }

}

@Composable
fun SunsetSunriseRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 15.dp,
                bottom = 6.dp,
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "sunrise",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = formatDateTime(weather.sunrise),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row {
            Image(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "sunset",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = formatDateTime(weather.sunset),
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }
}

@Composable
fun HumidityWindPressureRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(22.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "humidity",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = weather.humidity.toString() + "%",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "pressure",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = weather.pressure.toString() + "%",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "wind",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = weather.humidity.toString() + "%",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}

@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(imageUrl),
        contentDescription = "Weather Icon",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(80.dp)
            .wrapContentSize()
            .wrapContentHeight()
            .wrapContentWidth()
    )
}






