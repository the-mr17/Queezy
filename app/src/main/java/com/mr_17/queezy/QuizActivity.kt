package com.mr_17.queezy

import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.mr_17.queezy.question.Question

class QuizActivity : AppCompatActivity() {

    private var selectedCategory: TextView? = null
    private var selectedDifficulty: TextView? = null

    private var progressIndicator: CircularProgressIndicator? = null
    private var questionNumber: TextView? = null
    private var questionCountText: TextView? = null

    private var question: TextView? = null
    private var optionsGroup: RadioGroup? = null
    private var selectionOption: RadioButton? = null

    private var nextButton: Button? = null

    private var qno: Int = 0
    private var qAndA: Question? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        InitializeFields()

        nextButton!!.setOnClickListener {
            /*val selectedOption: Int = optionsGroup!!.checkedRadioButtonId

            // Assigning id of the checked radio button
            selectionOption = findViewById(selectedOption)

            // Displaying text of the checked radio button in the form of toast
            Toast.makeText(baseContext, optionsGroup?.indexOfChild(selectionOption).toString(), Toast.LENGTH_SHORT).show()*/

            SetNextQuestion()
        }
    }

    private fun InitializeFields()
    {
        selectedCategory = findViewById(R.id.selected_category)
        selectedDifficulty = findViewById(R.id.selected_difficulty)
        progressIndicator = findViewById(R.id.circularProgressIndicator)
        questionCountText = findViewById(R.id.question_count)
        questionNumber = findViewById(R.id.question_number)
        question = findViewById(R.id.question)
        optionsGroup = findViewById(R.id.options_group)
        nextButton = findViewById(R.id.next_button)

        val i = intent
        qAndA = i.getSerializableExtra("question") as Question?
        selectedCategory!!.setText(i.getSerializableExtra("category") as String?)
        selectedDifficulty!!.setText(i.getSerializableExtra("difficulty") as String?)

        SetNextQuestion()
    }

    private fun SetNextQuestion()
    {
        qno++

        questionNumber!!.text = qno.toString()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressIndicator?.setProgress(qno  * 100 / qAndA!!.question!!.size, true)
        }
        questionCountText!!.setText("Question " + qno + " of " + qAndA!!.question!!.size)
        question!!.setText(qAndA!!.question!!.get(qno-1))

        (optionsGroup!!.getChildAt(0) as RadioButton?)!!.setText(qAndA!!.optA!!.get(qno-1))
        (optionsGroup!!.getChildAt(1) as RadioButton?)!!.setText(qAndA!!.optB!!.get(qno-1))
        (optionsGroup!!.getChildAt(2) as RadioButton?)!!.setText(qAndA!!.optC!!.get(qno-1))
        (optionsGroup!!.getChildAt(3) as RadioButton?)!!.setText(qAndA!!.optD!!.get(qno-1))

        optionsGroup!!.clearCheck()
    }
}