package com.soumyadeep.pingme.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.soumyadeep.pingme.model.feature_auth_user
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import android.app.Activity
import android.util.Log
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.FirebaseException
import android.content.Context
import com.google.firebase.auth.PhoneAuthOptions
import java.util.concurrent.TimeUnit
import  com.google.firebase.Firebase
import android.graphics.Bitmap
import com.google.firebase.auth.auth
import java.io.ByteArrayOutputStream
import android.util.Base64
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

@HiltViewModel


class FeatureAuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val database: FirebaseDatabase,
    private val storage: FirebaseStorage
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Ideal)
    val authState = _authState.asStateFlow()
    private val _startDestination = MutableStateFlow<String?>(null) // ✅ add this
    val startDestination = _startDestination.asStateFlow()         // expose as read-only
    private val usersRef = database.getReference("users")

    /** Send verification code to phone number */
    fun sendVerificationCode(phoneNumber: String, activity: Activity) {
        _authState.value = AuthState.Loading

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                _authState.value = AuthState.CodeSent(verificationId)
            }

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signWithCredential(credential, activity)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.e("FeatureAuthVM", "Verification failed: ${e.message}")
                _authState.value = AuthState.Error(e.message ?: "Verification failed")
            }
        }

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    /** Verify OTP entered by user */
    fun verifyCode(otp: String, verificationId: String?, context: Context) {
        if (verificationId == null) {
            _authState.value = AuthState.Error("Verification ID missing")
            return
        }
        val credential = PhoneAuthProvider.getCredential(verificationId, otp)
        signWithCredential(credential, context)
    }
    /** Sign in using phone credential */
    private fun signWithCredential(credential: PhoneAuthCredential, context: Context) {
        _authState.value = AuthState.Loading

        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = firebaseAuth.currentUser?.uid ?: return@addOnCompleteListener

                    usersRef.child(userId).get().addOnSuccessListener { snapshot ->
                        val profile = snapshot.getValue(feature_auth_user::class.java)
                        val user = firebaseAuth.currentUser
                        val phoneAuthUser = feature_auth_user(
                            userId = userId,
                            phoneNumber = user?.phoneNumber ?: "",
                            name = profile?.name ?: "",
                            status = profile?.status ?: "",
                            profileImage = profile?.profileImage
                        )

                        _authState.value = AuthState.Success(phoneAuthUser)
                    }
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Sign-in failed")
                }
            }
    }


    fun resetAuthState() {
        _authState.value = AuthState.Idle
    }

    /** Mark user as signed in in shared prefs */
    private fun markUserAsSignedIn(context: Context) {
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        prefs.edit().putBoolean("isSignedIn", true).apply()
    }

    /** Fetch user profile from database */
    private fun fetchUserProfile(userId: String) {
        usersRef.child(userId).get()
            .addOnSuccessListener { snapshot ->
                val profile = snapshot.getValue(feature_auth_user::class.java)
                profile?.let {
                    _authState.value = AuthState.Success(it)
                }
            }
            .addOnFailureListener {
                _authState.value = AuthState.Error("Failed to fetch user profile")
            }
    }

    /** Save user profile with optional profile image (uploads to Firebase Storage) */
    fun saveUserProfile(
        userId: String,
        name: String,
        status: String,
        profileImage: Bitmap?,
        context: Context
    ) {
        viewModelScope.launch {
            val imageUrl = profileImage?.let { uploadProfileImage(userId, it) }

            val userProfile = feature_auth_user(
                userId = userId,
                name = name,
                status = status,
                phoneNumber = firebaseAuth.currentUser?.phoneNumber ?: "",
                profileImage = imageUrl
            )

            usersRef.child(userId).setValue(userProfile)
                .addOnSuccessListener { _authState.value = AuthState.Success(userProfile) }
                .addOnFailureListener {
                    _authState.value = AuthState.Error("Failed to save profile")
                }
        }
    }

    /** Upload bitmap to Firebase Storage and return URL */
    private suspend fun uploadProfileImage(userId: String, bitmap: Bitmap): String? {
        return try {
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos)
            val data = baos.toByteArray()

            val ref = storage.reference.child("profile_images/$userId.jpg")
            ref.putBytes(data).await()
            ref.downloadUrl.await().toString()
        } catch (e: Exception) {
            Log.e("FeatureAuthVM", "Image upload failed: ${e.message}")
            null
        }
    }

    /** Reset auth state */

    /** Sign out user */
    fun signOut(activity: Activity) {
        firebaseAuth.signOut()
        activity.getSharedPreferences("app_prefs", Activity.MODE_PRIVATE)
            .edit().putBoolean("isSignedIn", false).apply()
    }
    fun checkStartDestination() {
        viewModelScope.launch {
            val user = FirebaseAuth.getInstance().currentUser
            if (user == null) {
                _startDestination.value = "splash"
            } else {
                val snapshot = FirebaseDatabase.getInstance().getReference("users/${user.uid}").get().await()
                val profile = snapshot.getValue(feature_auth_user::class.java)
                _startDestination.value = if (profile?.name.isNullOrEmpty() || profile?.status.isNullOrEmpty()) {
                    "set_profile"
                } else {
                    "main"
                }
            }
        }
    }
}

/** Auth state sealed class */
sealed class AuthState {
    object Ideal : AuthState()
    object Idle : AuthState()
    object Loading : AuthState()
    data class CodeSent(val verificationId: String) : AuthState()
    data class Success(val user: feature_auth_user) : AuthState()
    data class Error(val message: String) : AuthState()
}

