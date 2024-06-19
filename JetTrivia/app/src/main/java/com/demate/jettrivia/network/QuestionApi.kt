package com.demate.jettrivia.network

import com.demate.jettrivia.model.Question
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionApi {
    @GET("world.json")
    suspend fun getQuestions(): Question
}