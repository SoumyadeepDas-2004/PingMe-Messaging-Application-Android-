package com.soumyadeep.pingme.presentation.viewmodel

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soumyadeep.pingme.model.feature_auth_user
import com.soumyadeep.pingme.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import android.graphics.Bitmap

sealed class SetProfileState {
    object Idle : SetProfileState()
    object Loading : SetProfileState()
    data class Success(val user: feature_auth_user) : SetProfileState()
    data class Error(val message: String) : SetProfileState()
}

@HiltViewModel
class SetProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SetProfileState>(SetProfileState.Idle)
    val uiState: StateFlow<SetProfileState> = _uiState

    // temp holder for user input
    var userName: String = ""
    var about: String = ""
    var profileImageBase64: String? = null

    /**
     * Convert image from Uri to Base64 string
     */
    private fun uriToBase64(context: Context, uri: Uri): String {
        val inputStream = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos) // compress to reduce size
        val bytes = baos.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    /**
     * Handles image selection → convert to Base64 and store in memory
     */
    fun handleImageSelection(context: Context, imageUri: Uri) {
        profileImageBase64 = uriToBase64(context, imageUri)
    }

    /**
     * Save profile with image stored as Base64 string in Realtime DB
     */
    fun saveProfile(userId: String) {
        if (userName.isBlank()) {
            _uiState.value = SetProfileState.Error("Name cannot be empty")
            return
        }

        _uiState.value = SetProfileState.Loading   // 🔹 show loading before saving

        val user = feature_auth_user(
            userId = userId,
            name = userName,
            status = about,
            profileImage = profileImageBase64 // Base64 instead of URL
        )

        viewModelScope.launch {
            try {
                repository.updateProfile(user)
                _uiState.value = SetProfileState.Success(user)
            } catch (e: Exception) {
                _uiState.value = SetProfileState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
    fun resetState() {
        _uiState.value = SetProfileState.Idle // or whatever represents the default state
    }

}
