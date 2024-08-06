package com.demate.jetareader.screens.details

import androidx.lifecycle.ViewModel
import com.demate.jetareader.data.Resource
import com.demate.jetareader.model.Item
import com.demate.jetareader.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: BookRepository) : ViewModel() {

    suspend fun getBookDetails(bookId: String): Resource<Item> {
        return repository.getBookInfo(bookId)
    }


}