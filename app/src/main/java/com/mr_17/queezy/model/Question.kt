package com.mr_17.queezy.model

import android.content.Context
import com.mr_17.queezy.api.Result
import java.io.Serializable
import kotlin.collections.ArrayList

class Question : Serializable {
    @Transient
    var context: Context? = null
    var results: ArrayList<Result>? = null
    var question: ArrayList<String>? = null
    var optA: ArrayList<String>? = null
    var optB: ArrayList<String>? = null
    var optC: ArrayList<String>? = null
    var optD: ArrayList<String>? = null
    var Answer: ArrayList<Int>? = null

    constructor() {}
    constructor(context: Context?) {
        this.context = context
        question = ArrayList()
        results = ArrayList()
        optA = ArrayList()
        optB = ArrayList()
        optC = ArrayList()
        optD = ArrayList()
        Answer = ArrayList()
    }
}