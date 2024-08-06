package com.demate.jetareader.repository

import com.demate.jetareader.data.DataOrException
import com.demate.jetareader.model.MBook
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireRepository @Inject constructor(private val queryBook: Query) {

    suspend fun getAllBooksFromDatabase(): DataOrException<List<MBook>, Boolean, Exception> {
        val dataOrException = DataOrException<List<MBook>, Boolean, Exception>()
        try {
            dataOrException.loading = true
            queryBook.get().await().documents.map { document ->
                document.toObject(MBook::class.java)!!
            }
            if (!dataOrException.data.isNullOrEmpty()) dataOrException.loading = false
        } catch (exception: FirebaseFirestoreException) {
            dataOrException.e = exception
        }
        return dataOrException
    }
}