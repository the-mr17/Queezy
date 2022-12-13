package com.mr_17.queezy.model

data class SolutionsActivityRecyclerViewModel(
    var questionCount: String?,
    var questionStatus: String?,
    var question: String?,
    var optA: String?,
    var optB: String?,
    var optC: String?,
    var optD: String?,
    var correctOpt: Int?,
    var selectedOpt: Int?
    )
