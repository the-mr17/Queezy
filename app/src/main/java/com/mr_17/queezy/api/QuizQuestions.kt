package com.mr_17.queezy.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class QuizQuestions {
    @SerializedName("response_code")
    @Expose
    var responseCode: Int? = null
        private set

    @SerializedName("results")
    @Expose
    private var results: List<Result>? = null

    constructor() {}
    constructor(responseCode: Int?, results: List<Result>?) : super() {
        this.responseCode = responseCode
        this.results = results
    }

    fun getResults(): List<Result>? {
        return results
    }
}