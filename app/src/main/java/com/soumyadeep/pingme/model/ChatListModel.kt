package com.soumyadeep.pingme.model

data class ChatListModel(
    val userId: String = "",         // Firebase UID of the other user
    val phoneNumber: String = "",    // Their phone number
    val name: String = "",           // Display name
    val profileImageUrl: String = "",// Profile picture URL
    val lastMessage: String = "",    // Last message preview
    val lastMessageTime: Long = 0,   // Timestamp of last message
    val unreadCount: Int = 0
)