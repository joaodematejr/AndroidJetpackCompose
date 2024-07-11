package com.demate.jetweatherforecast.resository

import com.demate.jetweatherforecast.data.DataOrException
import com.demate.jetweatherforecast.data.WeatherDao
import com.demate.jetweatherforecast.model.Favorite
import com.demate.jetweatherforecast.model.Weather
import com.demate.jetweatherforecast.network.WeatherApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherApi,
    private val weatherDao: WeatherDao
) {
    suspend fun getWeather(cityQuery: String): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery)
        } catch (e: Exception) {
            return DataOrException(exception = e)
        }
        return DataOrException(data = response)
    }

    fun getFavoriteWeather(): Flow<List<Favorite>> = weatherDao.getFavorites()

    suspend fun insertFavorite(favorite: Favorite) = weatherDao.insertFavorite(favorite)

    suspend fun updateFavorite(favorite: Favorite) = weatherDao.updateFavorite(favorite)

    suspend fun deleteAllFavorite() = weatherDao.deleteAllFavorite()

    suspend fun deleteFavorite(favorite: Favorite) = weatherDao.deleteFavorite(favorite)

    suspend fun getFavoriteByCity(city: String): Favorite = weatherDao.getFavById(city)


}