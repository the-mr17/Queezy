package com.mr_17.queezy

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var startQuizButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        InitializeFields()

        startQuizButton!!.setOnClickListener { SendToActivity(QuizActivity::class.java, false) }
    }

    private fun InitializeFields() {
        startQuizButton = findViewById(R.id.start_quiz_button)
    }

    private fun SendToActivity(activityClass: Class<out Activity?>, backEnabled: Boolean) {
        val intent = Intent(this, activityClass)
        if (!backEnabled) intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        if (!backEnabled) finish()
    }
}