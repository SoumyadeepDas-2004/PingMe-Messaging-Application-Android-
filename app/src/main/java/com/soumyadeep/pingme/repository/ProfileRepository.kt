package com.soumyadeep.pingme.repository

import com.google.firebase.database.FirebaseDatabase
import com.soumyadeep.pingme.model.feature_auth_user
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.tasks.await

interface ProfileRepository {
    suspend fun updateProfile(user: feature_auth_user): Result<Unit>
}

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val db: FirebaseDatabase
) : ProfileRepository {

//    override suspend fun updateProfile(user: feature_auth_user): Result<Unit> {
//        return try {
//            val ref = db.getReference("users").child(user.userId)
//            ref.setValue(user).await() // waits for Firebase operation
//            Result.success(Unit)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
override suspend fun updateProfile(user: feature_auth_user): Result<Unit> {
    return try {
        val updates = mutableMapOf<String, Any>()
        updates["name"] = user.name
        updates["status"] = user.status
        user.profileImage?.let { updates["profileImage"] = it }

        val ref = db.getReference("users").child(user.userId)
        ref.updateChildren(updates).await() // only updates specific fields
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}

}