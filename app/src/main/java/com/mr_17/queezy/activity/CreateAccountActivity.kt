package com.mr_17.queezy.activity

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mr_17.queezy.R
import com.mr_17.queezy.model.FirebaseModel

class CreateAccountActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var createAccountButton: Button? = null

    private var firstName: EditText? = null
    private  var lastName:EditText? = null
    private  var userEmail:EditText? = null
    private  var userPassword:EditText? = null
    private  var confirmUserPassword:EditText? = null

    private var myAuth: FirebaseAuth? = null
    private var rootRef: DatabaseReference? = null

    private var loadingBar: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        myAuth = FirebaseAuth.getInstance()
        rootRef = FirebaseDatabase.getInstance().getReference()

        InitializeFields()

        createAccountButton!!.setOnClickListener{ CreateNewAccount() }

        toolbar!!.setNavigationOnClickListener { onBackPressed() }
    }

    private fun InitializeFields() {
        toolbar = findViewById(R.id.toolbar)
        createAccountButton = findViewById(R.id.create_account_button)
        firstName = findViewById(R.id.first_name_edittext)
        lastName = findViewById(R.id.last_name_edittext)
        userEmail = findViewById(R.id.email_address_edittext)
        userPassword = findViewById(R.id.password_edittext)
        confirmUserPassword = findViewById(R.id.confirm_password_edittext)

        loadingBar = ProgressDialog(this)
    }

    private fun CreateNewAccount() {
        val email = userEmail!!.text.toString()
        val password = userPassword!!.text.toString()
        val confirmPassword = confirmUserPassword!!.text.toString()
        val first_Name = firstName!!.text.toString()
        val last_Name = lastName!!.text.toString()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email Required...", Toast.LENGTH_LONG).show()
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password Required...", Toast.LENGTH_LONG).show()
        } else if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Password Confirmation Required...", Toast.LENGTH_LONG).show()
        } else if (TextUtils.isEmpty(first_Name)) {
            Toast.makeText(this, "First Name Required...", Toast.LENGTH_LONG).show()
        } else if (TextUtils.isEmpty(last_Name)) {
            Toast.makeText(this, "Last Name Required...", Toast.LENGTH_LONG).show()
        } else if (password != confirmPassword) {
            Toast.makeText(this, "Passwords Mismatch...", Toast.LENGTH_LONG).show()
        } else {
            loadingBar!!.setTitle("Creating New Account")
            loadingBar!!.setMessage("Please wait, while we are creating a new account for you...")
            loadingBar!!.setCanceledOnTouchOutside(false)
            loadingBar!!.show()
            myAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val currentUserID = myAuth!!.currentUser!!.uid
                        val profileMap = HashMap<String, String>()
                        profileMap[FirebaseModel.node_uid] = currentUserID
                        profileMap[FirebaseModel.node_firstName] = first_Name
                        profileMap[FirebaseModel.node_lastName] = last_Name

                        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        FirebaseModel.databaseRef_users.child(currentUserID)
                            .setValue("") // test this
                        //rootRef.child("Programs").child("subTopics").child("VIII").setValue("");
                        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        FirebaseModel.databaseRef_users.child(currentUserID).setValue(profileMap)
                            .addOnCompleteListener(
                                OnCompleteListener<Void?> {
                                    SendToActivity(LoginActivity::class.java, false)
                                    Toast.makeText(
                                        this@CreateAccountActivity,
                                        "Account Created Successfully. Login to continue.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    loadingBar!!.dismiss()
                                })
                    } else {
                        val message = task.exception!!.message
                        Toast.makeText(this@CreateAccountActivity, "Error: $message", Toast.LENGTH_LONG)
                            .show()
                        loadingBar!!.dismiss()
                    }
                }
        }
    }

    private fun SendToActivity(activityClass: Class<out Activity?>, backEnabled: Boolean) {
        val intent = Intent(this, activityClass)
        if (!backEnabled) intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        if (!backEnabled) finish()
    }
}