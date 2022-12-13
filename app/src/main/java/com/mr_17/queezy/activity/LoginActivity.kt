package com.mr_17.queezy.activity

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.mr_17.queezy.R

class LoginActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var loginButton: Button? = null
    private var userEmail: EditText? = null
    private  var userPassword:EditText? = null
    private  var forgotPasswordLink:TextView? = null

    private var loginWithGoogleButton: Button? = null
    private var loginWithFacebookButton: Button? = null

    private var myAuth: FirebaseAuth? = null

    private var loadingBar: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        InitializeFields() // initializing all elements

        // checking clicks respective buttons and text
        loginButton!!.setOnClickListener { AllowUserTolLogin() }

        forgotPasswordLink!!.setOnClickListener { v -> ForgotPassword(v) }

        loginWithGoogleButton!!.setOnClickListener {
            Toast.makeText(applicationContext, "This feature is not available yet. Please login using Email & Password", Toast.LENGTH_SHORT).show()
        }

        loginWithFacebookButton!!.setOnClickListener {
            Toast.makeText(applicationContext, "This feature is not available yet. Please login using Email & Password", Toast.LENGTH_SHORT).show()
        }

        toolbar!!.setNavigationOnClickListener { onBackPressed() }
    }

    private fun InitializeFields() {
        toolbar = findViewById(R.id.toolbar)
        loginButton = findViewById(R.id.login_button)
        loginWithGoogleButton = findViewById(R.id.login_with_google_button)
        loginWithFacebookButton = findViewById(R.id.login_with_fb_button)
        userEmail = findViewById(R.id.email_address_edittext)
        userPassword = findViewById(R.id.password_edittext)
        forgotPasswordLink = findViewById(R.id.forgot_password_button)
        loadingBar = ProgressDialog(this)
        myAuth = FirebaseAuth.getInstance()
    }

    private fun AllowUserTolLogin() {
        // storing the email and password as String
        val email = userEmail!!.text.toString()
        val password = userPassword!!.text.toString()

        // checking that none of the fields are empty and giving appropriate toasts
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email Required...", Toast.LENGTH_LONG).show()
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password Required...", Toast.LENGTH_LONG).show()
        } else {
            // setting up the loading bar
            loadingBar!!.setTitle("Signing In")
            loadingBar!!.setMessage("Please Wait...")
            loadingBar!!.setCanceledOnTouchOutside(false)
            loadingBar!!.show()

            // attempting to sign in using firebase
            myAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // sending user to main activity on successful sign in
                    SendToActivity(MainActivity::class.java, false)
                    //Toast.makeText(LoginActivity.this, "Logged in Successfully...", Toast.LENGTH_SHORT).show();
                    loadingBar!!.dismiss()
                } else {
                    // otherwise displaying the error message
                    val message = task.exception!!.message
                    Toast.makeText(this@LoginActivity, "Error: $message", Toast.LENGTH_LONG).show()
                    loadingBar!!.dismiss()
                }
            }
        }
    }

    private fun ForgotPassword(v: View) {
        // edit text for the alert dialog
        val resetEmail = EditText(v.context)
        resetEmail.hint = "Email Address"

        // setting up the alert dialog
        val passwordResetDialog = AlertDialog.Builder(v.context)
        passwordResetDialog.setTitle("Reset Password ?")
        passwordResetDialog.setMessage("Enter your e-mail to receive the reset link.")
        passwordResetDialog.setView(resetEmail)

        // creating functionality of the "yes" button
        passwordResetDialog.setPositiveButton(
            "Yes"
        ) { dialog, which ->
            // getting the email text from the edit text
            val mail = resetEmail.text.toString()
            // checking for empty edit text
            if (TextUtils.isEmpty(mail)) {
                Toast.makeText(this@LoginActivity, "Email Required...", Toast.LENGTH_LONG)
                    .show()
            } else {
                // resetting the password using firebase
                myAuth!!.sendPasswordResetEmail(mail).addOnSuccessListener {
                    Toast.makeText(
                        this@LoginActivity,
                        "Reset link has been sent to your e-mail.",
                        Toast.LENGTH_SHORT
                    ).show()
                }.addOnFailureListener { e -> // displaying a toast on failure
                    val message = e.message
                    Toast.makeText(this@LoginActivity, "Error: $message", Toast.LENGTH_LONG).show()
                }
            }
        }

        // creating the "no" button
        passwordResetDialog.setNegativeButton(
            "No"
        ) { dialog, which -> }
        passwordResetDialog.create().show()
    }

    private fun SendToActivity(activityClass: Class<out Activity?>, backEnabled: Boolean) {
        val intent = Intent(this, activityClass)
        if (!backEnabled) intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        if (!backEnabled) finish()
    }
}