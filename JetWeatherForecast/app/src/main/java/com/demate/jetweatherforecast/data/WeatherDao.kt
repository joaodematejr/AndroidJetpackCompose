package com.demate.jetweatherforecast.data

import androidx.room.Dao
import androidx.room.Query
import com.demate.jetweatherforecast.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query("SELECT * FROM fav_tbl")
    fun getFavorites(): Flow<List<Favorite>>

    @Query("SELECT * FROM fav_tbl WHERE city = :city")
    suspend fun getFavById(city: String): Favorite

}