package com.demate.jettrivia.repository

import com.demate.jettrivia.data.DataOrException
import com.demate.jettrivia.model.Question
import com.demate.jettrivia.model.QuestionItem
import com.demate.jettrivia.network.QuestionApi
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val api: QuestionApi
) {
    private val listOfQuestions = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()
    suspend fun getQuestions(): Question {
        return api.getQuestions()
    }
}