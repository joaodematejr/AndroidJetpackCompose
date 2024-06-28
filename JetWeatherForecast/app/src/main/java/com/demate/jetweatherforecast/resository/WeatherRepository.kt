package com.demate.jetweatherforecast.resository

import com.demate.jetweatherforecast.data.DataOrException
import com.demate.jetweatherforecast.model.Weather
import com.demate.jetweatherforecast.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {
    suspend fun getWeather(cityQuery: String): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery)
        } catch (e: Exception) {
            return DataOrException(exception = e)
        }
        return DataOrException(data = response)
    }
}