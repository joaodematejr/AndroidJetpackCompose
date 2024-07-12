package com.demate.jetweatherforecast.screens.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demate.jetweatherforecast.model.Favorite
import com.demate.jetweatherforecast.resository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: WeatherRepository) :
    ViewModel() {
    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavoriteWeather().distinctUntilChanged()
                .collect { listOfFav ->
                    if (listOfFav.isNotEmpty()) {
                        Log.d("FavoriteViewModel", "Favorite List: $listOfFav")
                    } else {
                        _favList.value = listOfFav
                    }
                }
        }
    }

    fun insertFavorite(favorite: Favorite) = viewModelScope.launch {
        repository.insertFavorite(favorite)
    }

    fun updateFavorite(favorite: Favorite) = viewModelScope.launch {
        repository.updateFavorite(favorite)
    }

    fun deleteFavorite(favorite: Favorite) = viewModelScope.launch {
        repository.deleteFavorite(favorite)
    }

    fun deleteAllFavorite() = viewModelScope.launch {
        repository.deleteAllFavorite()
    }

    fun getFavoriteByCity(city: String) = viewModelScope.launch {
        repository.getFavoriteByCity(city)
    }


}