package com.demate.jetweatherforecast.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.demate.jetweatherforecast.R
import com.demate.jetweatherforecast.model.Weather
import com.demate.jetweatherforecast.model.WeatherItem
import com.demate.jetweatherforecast.utils.formatDate
import com.demate.jetweatherforecast.utils.formatDateTime
import com.demate.jetweatherforecast.utils.formatDecimals

@Composable
fun MainScaffold(weather: Weather, navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = weather.city.name + weather.city.country,
            navController = navController,
            onAddActionClicked = {
                //navController.navigate(WeatherScreens.SearchScreen.name)
            }
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
        Text(
            text = "This Week",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = Color(0x32FFFFFF),
            shape = RoundedCornerShape(size = 14.dp)
        ) {
            LazyColumn(
                modifier = Modifier.padding(2.dp),
                contentPadding = PaddingValues(1.dp)
            ) {
                items(items = data.list) { item: WeatherItem ->
                    WeatherDetailRow(weather = item)
                }
            }
        }

    }

}

@Composable
fun WeatherDetailRow(weather: WeatherItem) {
    val imageUrl = "https://openweathermap.org/img/w/${weather.weather.first().icon}.png"
    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(2.dp)),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatDate(weather.dt).split(",")[0],
                modifier = Modifier.padding(start = 6.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            WeatherStateImage(imageUrl = imageUrl)
            Surface(
                modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFFFC400)
            ) {
                Text(
                    text = weather.weather.first().description,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(6.dp)
                )
            }
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue.copy(alpha = 0.8f),
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append(formatDecimals(weather.temp.max) + "°")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Gray.copy(alpha = 0.8f),
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append(formatDecimals(weather.temp.min) + "°")
                }
            })

        }

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