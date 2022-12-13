package com.mr_17.queezy.model

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object FirebaseModel {
    var node_users = "Users"
    var node_uid = "uid"
    var node_firstName = "firstName"
    var node_lastName = "lastName"
    var node_joiningDate = "joiningDate"

    var databaseRef_root: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    var databaseRef_users: DatabaseReference = databaseRef_root.child(node_users)
}