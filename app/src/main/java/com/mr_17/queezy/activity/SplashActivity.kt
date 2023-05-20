package com.mr_17.queezy.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.mr_17.queezy.R
import com.mr_17.queezy.model.FirebaseModel

class SplashActivity : AppCompatActivity() {

    private var myAuth: FirebaseAuth? = null
    private var currentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        InitializeFields()

        Handler().postDelayed({
            var intent: Intent
            /*if(currentUser == null) {
                intent = Intent(this@SplashActivity, WelcomeActivity::class.java)
            }
            else
            {
                CheckJoining()
                intent = Intent(this@SplashActivity, MainActivity::class.java)
            }*/
            intent = Intent(this@SplashActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }, 2000)
    }

    private fun InitializeFields() {
        myAuth = FirebaseAuth.getInstance()
        currentUser = myAuth!!.currentUser
    }

    private fun CheckJoining() {
        FirebaseModel.databaseRef_users.child(currentUser!!.uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (!snapshot.child(FirebaseModel.node_joiningDate).exists()) {
                        FirebaseModel.databaseRef_users.child(currentUser!!.uid)
                            .child(FirebaseModel.node_joiningDate).setValue(
                                currentUser!!.metadata!!.creationTimestamp
                            )
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }
}