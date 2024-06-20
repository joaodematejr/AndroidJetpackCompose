package com.demate.jettrivia.repository

import android.util.Log
import com.demate.jettrivia.data.DataOrException
import com.demate.jettrivia.model.QuestionItem
import com.demate.jettrivia.network.QuestionApi
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val api: QuestionApi,
) {
    private val dataOrException = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
        try {
            dataOrException.isLoading = true
            dataOrException.data = api.getQuestions()
            if (dataOrException.data.toString().isNotEmpty()) dataOrException.isLoading = false
        } catch (e: Exception) {
            dataOrException.exception = e
            Log.d("Exception", "getQuestions: ${dataOrException.exception!!.localizedMessage}")

        }
        return dataOrException
    }
}