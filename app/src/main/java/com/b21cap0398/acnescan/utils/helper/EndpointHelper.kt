package com.b21cap0398.acnescan.utils.helper

import com.b21cap0398.acnescan.data.source.local.entity.UserInformation
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

object EndpointHelper {

    val db = FirebaseFirestore.getInstance()

    fun getUserDocumentReference(email: String) : DocumentReference {
        return db.collection("users").document(email)
    }

    fun getAcneScanResultReference(email: String, result_id: String) : DocumentReference {
        return getUserDocumentReference(email).collection("results").document(result_id)
    }

    fun getAcneInformationReference(acneId: String) : DocumentReference {
        return db.collection("acnes").document(acneId)
    }

    fun getCommonAcnesReference() : CollectionReference {
        return db.collection("commonacnes")
    }

    fun getDailyReadReference() : CollectionReference {
        return db.collection("dailyread")
    }

    fun getAllAcneScanResult(email: String) : CollectionReference {
        return getUserDocumentReference(email).collection("results")
    }

    fun getAllPossibilitesReference(email: String, result_id: String) : CollectionReference {
        return getAcneScanResultReference(email, result_id).collection("possibilities")
    }
}