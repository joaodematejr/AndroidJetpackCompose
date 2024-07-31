package com.demate.jetareader.network

import com.demate.jetareader.model.Book
import com.demate.jetareader.model.Item
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface BooksApi {

    @GET("volumes")
    suspend fun getAllBooks(@Query("q") query: String): Book

    @GET("volumes/{bookId}")
    suspend fun getBook(@Path("bookId") bookId: String): Item


}