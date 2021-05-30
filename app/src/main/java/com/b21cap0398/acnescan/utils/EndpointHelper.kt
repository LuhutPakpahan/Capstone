package com.b21cap0398.acnescan.utils

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

object EndpointHelper {

    val db = FirebaseFirestore.getInstance()

    fun userRef(email: String): DocumentReference {
        return db.collection("users").document(email)
    }

    fun resultsScanRef(email: String):CollectionReference {
        return db.collection("users").document(email).collection("results")
    }

    fun possibilityAcneRef(email: String, result_num: String) : CollectionReference {
        return db.collection("users").document(email).collection("results").document(result_num).collection("possibilities")
    }

    fun acneRef(acneName: String): DocumentReference {
        return db.collection("acnes").document(acneName)
    }
}