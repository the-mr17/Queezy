package com.mr_17.queezy.api

import com.google.gson.annotations.SerializedName

class CategoryQuestionCount {
    @SerializedName("total_hard_question_count")
    var totalHardQuestionCount = 0

    @SerializedName("total_easy_question_count")
    var totalEasyQuestionCount = 0

    @SerializedName("total_medium_question_count")
    var totalMediumQuestionCount = 0

    @SerializedName("total_question_count")
    private val totalQuestionCount = 0
}