package com.mr_17.queezy.api

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import java.io.Serializable

class Result : Serializable {
    @SerializedName("category")
    @Expose
    var category: String? = null
        private set

    @SerializedName("type")
    @Expose
    var type: String? = null
        private set

    @SerializedName("difficulty")
    @Expose
    var difficulty: String? = null
        private set

    @SerializedName("question")
    @Expose
    var question: String? = null
        private set

    @SerializedName("correct_answer")
    @Expose
    var correctAnswer: String? = null
        private set

    @SerializedName("incorrect_answers")
    @Expose
    var incorrectAnswers: List<String>? = null
        private set

    constructor() {}
    constructor(
        category: String?,
        type: String?,
        difficulty: String?,
        question: String?,
        correctAnswer: String?,
        incorrectAnswers: List<String>?
    ) : super() {
        this.category = category
        this.type = type
        this.difficulty = difficulty
        this.question = question
        this.correctAnswer = correctAnswer
        this.incorrectAnswers = incorrectAnswers
    }
}