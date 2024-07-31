package com.demate.jetareader.repository

import com.demate.jetareader.data.DataOrException
import com.demate.jetareader.model.Item
import com.demate.jetareader.network.BooksApi
import javax.inject.Inject

class BookRepository @Inject constructor(private val booksApi: BooksApi) {
    private val dataOrException = DataOrException<List<Item>, Boolean, Exception>()
    private val bookInfoDataOrException = DataOrException<Item, Boolean, Exception>()

    suspend fun getAllBooks(searchQuery: String): DataOrException<List<Item>, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = booksApi.getAllBooks(searchQuery).items
            if (dataOrException.data!!.isEmpty()) dataOrException.loading = false

        } catch (e: Exception) {
            dataOrException.e = e
        }
        return dataOrException
    }

    suspend fun getBookInfo(bookId: String): DataOrException<Item, Boolean, Exception> {
        val response = try {
            bookInfoDataOrException.loading = true
            bookInfoDataOrException.data = booksApi.getBook(bookId)
            if (bookInfoDataOrException.data.toString()
                    .isNotEmpty()
            ) bookInfoDataOrException.loading = false
            else {
            }
        } catch (e: Exception) {
            bookInfoDataOrException.e = e
        }
        return bookInfoDataOrException
    }
}