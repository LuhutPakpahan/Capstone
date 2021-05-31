package com.b21cap0398.acnescan.data.source.firebase

import android.util.Log
import com.b21cap0398.acnescan.data.source.local.entity.AcneInformation
import com.b21cap0398.acnescan.data.source.local.entity.AcneScanResult
import com.b21cap0398.acnescan.data.source.local.entity.Possibility
import com.b21cap0398.acnescan.data.source.local.entity.UserInformation
import com.b21cap0398.acnescan.utils.helper.EndpointHelper
import com.google.firebase.auth.UserInfo

class FirebaseDataSource {
    companion object {
        @Volatile
        private var instance: FirebaseDataSource? = null

        fun getInstance(): FirebaseDataSource =
            instance ?: synchronized(this) {
                instance ?: FirebaseDataSource()
            }
    }

    suspend fun getAllPossibilities(
        email: String,
        result_id: String,
        callback: LoadAllPossibilitiesCallback
    ) {

        val possibilities = EndpointHelper.getAllPossibilitesReference(email, result_id)
        possibilities.get().addOnSuccessListener { documents ->
            if (documents != null) {
                Log.d("tag", documents.documents.size.toString())
                val list = ArrayList<Possibility>()
                for (document in documents) {
                    list.add(
                        Possibility(
                            document["acne_name"] as String,
                            document["possibility"] as Long
                        )
                    )
                }

                callback.onAllPosibilitiesReceived(list)
            }
        }
    }

    suspend fun getAcneScanResult(email: String, result_id: String, callback: LoadAcneScanResultCallback) {

        val acneResultDocument = EndpointHelper.getAcneScanResultReference(email, result_id)
        Log.d("snapshot", acneResultDocument.get().toString())

        acneResultDocument.get().addOnSuccessListener { document ->
            if (document != null) {
                val result = AcneScanResult(
                    result_id = document.id,
                    image_path = document["image_path"] as String,
                    date = document["date"] as String,
                    status = document["status"] as String
                )
                callback.onAcneScanResultReceived(result)
            }
        }
            .addOnFailureListener {
                Log.d("firestore", "Fail to get the document")
            }
    }

    @Suppress("UNCHECKED_CAST")
    suspend fun getAcneInformationById(acneId: String, callback: LoadAcneInformationCallback) {

        val acneInformationResultDocument = EndpointHelper.getAcneInformationReference(acneId)

        acneInformationResultDocument.get().addOnSuccessListener { document ->
            if (document != null) {
                val result = AcneInformation(
                    listImagePaths = document["acne_images"] as List<String>,
                    causes = document["causes"] as String,
                    description = document["description"] as String,
                    tips = document["tips"] as String)

                callback.onAcneInformationReceived(result)
            }
        }
    }

    suspend fun getAllAcceptedAcneScanResult(email: String, callback: LoadAllAcceptedAcneScanResultCallback) {

        val listAcneInformation = EndpointHelper.getAllAcneScanResult(email)

        listAcneInformation.get().addOnSuccessListener { documents ->
            if (documents != null) {
                Log.d("tag", documents.documents.size.toString())
                val list = ArrayList<AcneScanResult>()
                for (document in documents) {
                    if (document["status"] == "accepted") {
                        list.add(
                            AcneScanResult(
                                result_id = document.id,
                                image_path = document["image_path"] as String,
                                date = document["date"] as String,
                                status = document["status"] as String)
                        )
                    }
                }
                callback.onAllAcceptedAcneScanResultReceived(list)
            }
        }
    }

    suspend fun getAllRejectedAcneScanResult(email: String, callback: LoadAllRejectedAcneScanResultCallback) {

        val listAcneInformation = EndpointHelper.getAllAcneScanResult(email)

        listAcneInformation.get().addOnSuccessListener { documents ->
            if (documents != null) {
                Log.d("tag", documents.documents.size.toString())
                val list = ArrayList<AcneScanResult>()
                for (document in documents) {
                    if (document["status"] == "rejected") {
                        list.add(
                            AcneScanResult(
                                result_id = document.id,
                                image_path = document["image_path"] as String,
                                date = document["date"] as String,
                                status = document["status"] as String)
                        )
                    }
                }
                callback.onAllRejectedAcneScanResultReceived(list)
            }
        }
    }

    suspend fun setUserInformation(email: String, firstName: String, lastName: String, age: Long, gender: String) {
        val registerEndpoint = EndpointHelper.getUserDocumentReference(email)

        val userInformation = UserInformation(
            first_name = firstName,
            last_name = lastName,
            age = age,
            gender = gender
        )

        registerEndpoint.set(userInformation).addOnSuccessListener {
            Log.d("tag", "Successfully written in firestore")
        }
    }

    suspend fun getUserInformation(email: String, callback: LoadUserInformationCallback) {
        val userInformationDocument = EndpointHelper.getUserDocumentReference(email)

        userInformationDocument.get().addOnSuccessListener { document ->
            if (document != null) {
                val userInformation = UserInformation(
                    first_name = document["first_name"] as String,
                    last_name = document["last_name"] as String,
                    age = document["age"] as Long,
                    gender = document["gender"] as String
                )

                callback.onUserInformationReceived(userInformation)
            }
        }
    }

    interface LoadAllAcceptedAcneScanResultCallback {
        fun onAllAcceptedAcneScanResultReceived(acneScanResults: List<AcneScanResult>)
    }

    interface LoadAllRejectedAcneScanResultCallback {
        fun onAllRejectedAcneScanResultReceived(acneScanResults: List<AcneScanResult>)
    }

    interface LoadAcneInformationCallback {
        fun onAcneInformationReceived(acneInformation: AcneInformation)
    }

    interface LoadAcneScanResultCallback {
        fun onAcneScanResultReceived(acneScanResult: AcneScanResult)
    }

    interface LoadAllPossibilitiesCallback {
        fun onAllPosibilitiesReceived(possibilities: List<Possibility>)
    }

    interface LoadUserInformationCallback {
        fun onUserInformationReceived(userInformation: UserInformation)
    }
}
