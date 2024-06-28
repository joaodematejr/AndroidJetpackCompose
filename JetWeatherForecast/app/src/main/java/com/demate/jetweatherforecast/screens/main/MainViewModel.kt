package com.demate.jetweatherforecast.screens.main

import androidx.lifecycle.ViewModel
import com.demate.jetweatherforecast.data.DataOrException
import com.demate.jetweatherforecast.model.Weather
import com.demate.jetweatherforecast.resository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    suspend fun getWeatherData(city: String): DataOrException<Weather, Boolean, Exception> {
        return repository.getWeather(cityQuery = city)
    }

}