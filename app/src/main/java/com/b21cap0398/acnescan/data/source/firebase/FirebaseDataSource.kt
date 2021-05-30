package com.b21cap0398.acnescan.data.source.firebase

import com.b21cap0398.acnescan.data.source.local.entity.AcneScanResult
import com.b21cap0398.acnescan.data.source.local.entity.Possibility
import com.b21cap0398.acnescan.utils.helper.EndpointHelper
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import java.util.*

class FirebaseDataSource {
    companion object {
        @Volatile
        private var instance: FirebaseDataSource? = null

        fun getInstance(): FirebaseDataSource =
            instance ?: synchronized(this) {
                instance ?: FirebaseDataSource()
            }
    }

    suspend fun getAcneScanResult(email: String, pos: Int) : AcneScanResult {
        var result: AcneScanResult? = null

        val acneResultDocument = EndpointHelper.getAcneScanResultReference(email, pos)
        val possibilitiesReference = acneResultDocument.collection("possibilities")
        val possibilities = arrayListOf<Possibility>()


        possibilitiesReference.get().addOnSuccessListener { documents ->
            for (document in documents) {
                val possibility = Possibility(
                    acne_name = document["acne_name"] as String,
                    possibility = document["possibility"] as Int
                )

                possibilities.add(possibility)
            }
        }

        acneResultDocument.get().addOnSuccessListener { document ->
            if (document != null) {
                result = AcneScanResult(
                    imagePath = document["imagePath"] as String,
                    date = document["date"] as String,
                    possibilities = possibilities
                )
            }
        }

        return result as AcneScanResult
    }
}