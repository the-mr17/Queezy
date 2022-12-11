package com.mr_17.queezy

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class WelcomeActivity : AppCompatActivity() {

    private var loginButton: Button? = null
    private var createAccountButton: Button? = null
    private var laterButton: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        InitializeFields()

        // checking clicks respective buttons and text
        loginButton!!.setOnClickListener { SendToActivity(LoginActivity::class.java, true) }
        createAccountButton!!.setOnClickListener { SendToActivity(CreateAccountActivity::class.java, true) }
        laterButton!!.setOnClickListener { SendToActivity(MainActivity::class.java, false) }
    }

    private fun InitializeFields() {
        loginButton = findViewById(R.id.login_button)
        createAccountButton = findViewById(R.id.create_account_button)
        laterButton = findViewById(R.id.later_button)
    }

    private fun SendToActivity(activityClass: Class<out Activity?>, backEnabled: Boolean) {
        val intent = Intent(this, activityClass)
        if (!backEnabled) intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        if (!backEnabled) finish()
    }
}