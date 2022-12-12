package com.mr_17.queezy

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
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
    private var submitButton: Button? = null

    private var qno: Int = 0
    private var qAndA: Question? = null

    private var selectedOptions: ArrayList<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        InitializeFields()

        nextButton!!.setOnClickListener {
            SetNextQuestion()
        }

        submitButton!!.setOnClickListener {
            AddSelectedOptions()
            /*Toast.makeText(
                baseContext,
                selectedOptions.toString(),
                Toast.LENGTH_SHORT
            ).show()*/
            submitButton!!.isClickable = false
            //question!!.setText(selectedOptions.toString())

            val intent = Intent(this@QuizActivity, StatsActivity::class.java)
            intent.putExtra("qAndA", qAndA)
            intent.putIntegerArrayListExtra("selectedOptions", selectedOptions)
            startActivity(intent)
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
        submitButton = findViewById(R.id.submit_button)

        selectedOptions = ArrayList()

        qAndA = intent.getSerializableExtra("question") as Question?
        selectedCategory!!.setText(intent.getSerializableExtra("category") as String?)
        selectedDifficulty!!.setText(intent.getSerializableExtra("difficulty") as String?)

        SetNextQuestion()
    }

    private fun SetNextQuestion()
    {
        if(qno >= 1) {
            AddSelectedOptions()
        }

        if(++qno == qAndA!!.question!!.size)
        {
            nextButton!!.visibility = View.GONE
            submitButton!!.visibility = View.VISIBLE
        }

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

    private fun AddSelectedOptions()
    {
        val selectedOption: Int = optionsGroup!!.checkedRadioButtonId

        // Assigning id of the checked radio button
        selectionOption = findViewById(selectedOption)

        // store the selected option
        selectedOptions!!.add(optionsGroup?.indexOfChild(selectionOption)!! + 1)
    }
}