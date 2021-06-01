package com.b21cap0398.acnescan.data.source.firebase

import android.graphics.Bitmap
import android.util.Log
import com.b21cap0398.acnescan.data.source.local.entity.*
import com.b21cap0398.acnescan.utils.helper.FirebaseStorageEndpointHelper
import com.b21cap0398.acnescan.utils.helper.FirestoreEndpointHelper
import com.b21cap0398.acnescan.utils.helper.ProgressBarOperator
import java.io.ByteArrayOutputStream

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

        val possibilities = FirestoreEndpointHelper.getAllPossibilitesReference(email, result_id)
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

    suspend fun getAcneScanResult(
        email: String,
        result_id: String,
        callback: LoadAcneScanResultCallback
    ) {

        val acneResultDocument =
            FirestoreEndpointHelper.getAcneScanResultReference(email, result_id)
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

        val acneInformationResultDocument =
            FirestoreEndpointHelper.getAcneInformationReference(acneId)

        acneInformationResultDocument.get().addOnSuccessListener { document ->
            if (document != null) {
                val result = AcneInformation(
                    listImagePaths = document["acne_images"] as List<String>,
                    causes = document["causes"] as String,
                    description = document["description"] as String,
                    tips = document["tips"] as String
                )

                callback.onAcneInformationReceived(result)
            }
        }
    }

    suspend fun setResultPhoto(
        bitmap: Bitmap,
        email: String,
        result_id: String,
        callback: UploadPhotoCallback
    ) {
        val scanResultPhotoPath = FirebaseStorageEndpointHelper.getAcnePhotoPath(email, result_id)

        ProgressBarOperator.setProgressBarValue(70)
        ProgressBarOperator.setDescrtiptionTextView("Uploading a photo ...")

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val uploadTask = scanResultPhotoPath.putBytes(data)

        uploadTask
            .addOnSuccessListener {
                ProgressBarOperator.setProgressBarValue(100)
                ProgressBarOperator.setDescrtiptionTextView("Photo has been uploaded successfully")
                callback.onPhotoUploaded(it.metadata?.path.toString())
            }
            .addOnProgressListener {
                val progress = (100 * it.bytesTransferred) / it.totalByteCount
                ProgressBarOperator.setProgressBarValue(progress.toInt())
            }
    }

    suspend fun setScanResultAndPossibilites(
        email: String,
        result_id: String,
        acneScanResult: AcneScanResult,
        possibilities: List<Possibility>
    ) {
        ProgressBarOperator.setDescrtiptionTextView("Preparing acne result to upload ...")
        ProgressBarOperator.setProgressBarValue(0)
        val acneResultDocument =
            FirestoreEndpointHelper.getAcneScanResultReference(email, result_id)
        val acneScanResultToUpload = AcneScanResultToUpload(
            image_path = acneScanResult.image_path,
            date = acneScanResult.date,
            status = acneScanResult.status
        )

        acneResultDocument.set(acneScanResultToUpload).addOnSuccessListener {
            ProgressBarOperator.setProgressBarValue(20)
            ProgressBarOperator.setDescrtiptionTextView("Successfully to upload acne result ...")
        }

        val possibilitiesRef = FirestoreEndpointHelper.getAllPossibilitesReference(email, result_id)
        ProgressBarOperator.setDescrtiptionTextView("Preparing possibilities to upload ...")
        val progressIncrement = 30 / possibilities.size
        for (possibility in possibilities) {
            ProgressBarOperator.setProgressBarValue(ProgressBarOperator.getProgressBarValue() + progressIncrement)
            ProgressBarOperator.setDescrtiptionTextView("Uploading possibility data ...")
            possibilitiesRef.document().set(possibility)
        }

        ProgressBarOperator.setDescrtiptionTextView("Uploading is complete")
        ProgressBarOperator.setProgressBarValue(600)
    }

    suspend fun getAllAcceptedAcneScanResult(
        email: String,
        callback: LoadAllAcceptedAcneScanResultCallback
    ) {

        val listAcneInformation = FirestoreEndpointHelper.getAllAcneScanResult(email)

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
                                status = document["status"] as String
                            )
                        )
                    }
                }
                callback.onAllAcceptedAcneScanResultReceived(list)
            }
        }
    }

    suspend fun getAllRejectedAcneScanResult(
        email: String,
        callback: LoadAllRejectedAcneScanResultCallback
    ) {

        val listAcneInformation = FirestoreEndpointHelper.getAllAcneScanResult(email)

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
                                status = document["status"] as String
                            )
                        )
                    }
                }
                callback.onAllRejectedAcneScanResultReceived(list)
            }
        }
    }

    suspend fun setUserInformation(
        email: String,
        firstName: String,
        lastName: String,
        age: Long,
        gender: String
    ) {
        val registerEndpoint = FirestoreEndpointHelper.getUserDocumentReference(email)

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
        val userInformationDocument = FirestoreEndpointHelper.getUserDocumentReference(email)

        userInformationDocument.get().addOnSuccessListener { document ->
            if (document != null) {
                val userInformation = UserInformation(
                    first_name = document["first_name"] as String?,
                    last_name = document["last_name"] as String?,
                    age = document["age"] as Long?,
                    gender = document["gender"] as String?
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
        fun onAllPosibilitiesReceived(possibilities: ArrayList<Possibility>)
    }

    interface LoadUserInformationCallback {
        fun onUserInformationReceived(userInformation: UserInformation)
    }

    interface UploadPhotoCallback {
        fun onPhotoUploaded(photoPath: String)
    }
}
