package com.example.activity2.repository

import com.example.activity2.model.Complaint
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class ComplaintRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val complaintsCollection = firestore.collection("complaints")

    suspend fun addComplaint(complaint: Complaint): Result<Unit> {
        return try {
            val docRef = complaintsCollection.document()
            val complaintWithId = complaint.copy(id = docRef.id)
            docRef.set(complaintWithId).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getComplaints(onSuccess: (List<Complaint>) -> Unit, onFailure: (Exception) -> Unit) {
        complaintsCollection.orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailure(error)
                    return@addSnapshotListener
                }
                val complaints = value?.toObjects(Complaint::class.java) ?: emptyList()
                onSuccess(complaints)
            }
    }
}