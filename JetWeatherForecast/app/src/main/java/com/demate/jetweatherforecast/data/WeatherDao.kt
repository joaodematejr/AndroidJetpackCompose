package com.demate.jetweatherforecast.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.demate.jetweatherforecast.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query("SELECT * FROM fav_tbl")
    fun getFavorites(): Flow<List<Favorite>>

    @Query("SELECT * FROM fav_tbl WHERE city = :city")
    suspend fun getFavById(city: String): Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite) {
        insertFavorite(favorite)
    }

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: Favorite) {
        updateFavorite(favorite)
    }

    @Query("DELETE FROM fav_tbl")
    suspend fun deleteAllFavorite() {
        deleteAllFavorite()
    }

    @Delete
    suspend fun deleteFavorite(favorite: Favorite) {
        deleteFavorite(favorite)
    }

}