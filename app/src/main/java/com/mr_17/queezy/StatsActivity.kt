package com.mr_17.queezy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.mr_17.queezy.question.Question

class StatsActivity : AppCompatActivity() {

    private var qAndA: Question? = null

    private var selectedOptions: ArrayList<Int>? = null
    private var answer: ArrayList<Int>? = null

    private var correctAnswerCount: Int = 0
    private var incorrectAnswerCount: Int = 0
    private var completionPercentage: Double = 0.0
    private var skipped: Int = 0

    private var quizPointsTextView: TextView? = null
    private var correctAnswerCountTextView: TextView? = null
    private var incorrectAnswerCountTextView: TextView? = null
    private var completionPercentageTextView: TextView? = null
    private var skippedTextView: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        InitializeFields()
        GetStats()
        SetStats()
    }

    private fun InitializeFields()
    {
        qAndA = intent.getSerializableExtra("qAndA") as Question?
        selectedOptions = intent.getSerializableExtra("selectedOptions") as ArrayList<Int>?
        answer = qAndA!!.Answer

        quizPointsTextView = findViewById(R.id.results)
        correctAnswerCountTextView = findViewById(R.id.correct_answer_count)
        incorrectAnswerCountTextView = findViewById(R.id.incorrect_answers_count)
        completionPercentageTextView = findViewById(R.id.completion_percent)
        skippedTextView = findViewById(R.id.skipped_count)
    }

    private fun GetStats()
    {
        for (i in answer!!.indices)
        {
            if(selectedOptions!!.get(i) == 0)
            {
                skipped++
            }
            else if(answer!!.get(i) == selectedOptions!!.get(i))
            {
                correctAnswerCount++
            }
            else
            {
                incorrectAnswerCount++
            }
        }
        completionPercentage = (((correctAnswerCount.toDouble() + incorrectAnswerCount.toDouble()) / qAndA!!.question!!.size.toDouble()) * 100.0)
    }

    private fun SetStats()
    {
        quizPointsTextView!!.setText("You get ${(correctAnswerCount - incorrectAnswerCount) * 10} Quiz Points")
        correctAnswerCountTextView!!.setText(correctAnswerCount.toString())
        incorrectAnswerCountTextView!!.setText(incorrectAnswerCount.toString())
        skippedTextView!!.setText(skipped.toString())
        completionPercentageTextView!!.setText("${completionPercentage.toInt()}%")
        correctAnswerCountTextView!!.setText(correctAnswerCount.toString())
    }
}