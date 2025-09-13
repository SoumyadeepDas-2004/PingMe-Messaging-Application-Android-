package com.soumyadeep.pingme.model

data class feature_auth_user(
    val userId: String = "",
    val phoneNumber: String = "",
    val name: String = "",
    val status: String = "",
    val profileImage: String? = null
)