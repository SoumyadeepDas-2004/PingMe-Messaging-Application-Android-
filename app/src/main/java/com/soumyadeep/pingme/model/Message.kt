package com.soumyadeep.pingme.model

data class Message(
    val senderPhoneNumber: String = "",
    val message: String = "",
    val timeStamp: Long = 0L
)
