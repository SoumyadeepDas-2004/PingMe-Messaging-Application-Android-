package com.soumyadeep.pingme.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase
) : ViewModel() {

    private val _startDestination = MutableStateFlow<String>("splash")
    val startDestination: StateFlow<String> = _startDestination

    fun decideStartDestination() {
        viewModelScope.launch {
            val currentUser = auth.currentUser

            if (currentUser == null) {
                // No Firebase user → first-time launch
                _startDestination.value = "onboard"
                return@launch
            }

            try {
                // Check if phone exists in database (this is your actual verification of "signed in")
                val snapshot = database.getReference("users/${currentUser.uid}/phoneNumber").get().await()
                val phone = snapshot.getValue(String::class.java)

                _startDestination.value = if (!phone.isNullOrBlank()) {
                    "main"           // fully signed-in
                } else {
                    "onboard"
                }
            } catch (e: Exception) {
                // fallback
                _startDestination.value = "onboard"
            }
        }
    }

}
