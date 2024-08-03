package com.demate.jetareader.repository

import com.demate.jetareader.data.DataOrException
import com.demate.jetareader.data.Resource
import com.demate.jetareader.model.Item
import com.demate.jetareader.network.BooksApi
import javax.inject.Inject

class BookRepository @Inject constructor(private val booksApi: BooksApi) {
    private val dataOrException = DataOrException<List<Item>, Boolean, Exception>()
    private val bookInfoDataOrException = DataOrException<Item, Boolean, Exception>()


    suspend fun getAllBooks(searchQuery: String): Resource<List<Item>> {
        return try {
            Resource.Loading(data = "Loading...")
            val itemList = booksApi.getAllBooks(searchQuery).items
            if (itemList.isNotEmpty()) Resource.Loading(data = false)
            Resource.Success(data = itemList)
        } catch (exception: Exception) {
            Resource.Error(message = exception.message)
        }
    }

    suspend fun getBookInfo(bookId: String): Resource<Item> {
        val response = try {
            Resource.Loading(data = "Loading...")
            booksApi.getBook(bookId)
        } catch (exception: Exception) {
            return Resource.Error(message = exception.message)

        }
        Resource.Loading(data = false)
        return Resource.Success(data = response)
    }
}