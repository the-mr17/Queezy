package com.mr_17.queezy.api

import com.google.gson.annotations.SerializedName
import com.mr_17.queezy.api.CategoryQuestionCount

class ApiCount {
    @SerializedName("category_id")
    var categoryId = 0

    @SerializedName("category_question_count")
    var categoryQuestionCount: CategoryQuestionCount? = null
}