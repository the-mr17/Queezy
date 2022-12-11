package com.mr_17.queezy.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {
    @GET("api.php")
    fun getQuizQuestions(
        @Query("encode") encode: String?,
        @Query("amount") amount: Int?,
        @Query("difficulty") difficulty: String?,
        @Query("category") category: Int?
    ): Call<QuizQuestions>

    @GET("api_count.php")
    fun getQuizQuestions(
        @Query("category") category: Int?
    ): Call<ApiCount>

    companion object {
        const val BASE_URL = "https://opentdb.com/"
    }
}
