
package com.soumyadeep.pingme.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.soumyadeep.pingme.model.ChatListModel
import com.soumyadeep.pingme.model.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BaseViewModel : ViewModel() {

    private val _chatList = MutableStateFlow<List<ChatListModel>>(emptyList())
    val chatList = _chatList.asStateFlow()

    init {
        loadChatData { chatList -> _chatList.value = chatList }
    }

    // 🔍 Search user by phone number
    fun searchUserByPhoneNumber(phoneNumber: String, callback: (ChatListModel?) -> Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            Log.e("BaseViewModel", "User not authenticated")
            callback(null)
            return
        }

        val databaseReference = FirebaseDatabase.getInstance().getReference("users")
        databaseReference.orderByChild("phoneNumber").equalTo(phoneNumber)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.children.firstOrNull()
                        ?.getValue(ChatListModel::class.java)
                    callback(user)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("BaseViewModel", "DB error: ${error.message}")
                    callback(null)
                }
            })
    }

    // 📩 Send message (mirrors message for both users)
    fun sendMessage(senderPhoneNumber: String, receiverPhoneNumber: String, messageText: String) {
        val databaseReference = FirebaseDatabase.getInstance().reference
        val messageId = databaseReference.push().key ?: return

        val message = Message(
            senderPhoneNumber = senderPhoneNumber,
            message = messageText,
            timeStamp = System.currentTimeMillis()
        )

        // Sender side
        databaseReference.child("messages")
            .child(senderPhoneNumber)
            .child(receiverPhoneNumber)
            .child(messageId)
            .setValue(message)

        // Receiver side
        databaseReference.child("messages")
            .child(receiverPhoneNumber)
            .child(senderPhoneNumber)
            .child(messageId)
            .setValue(message)

        // Update chat list for both users
        updateChatList(senderPhoneNumber, receiverPhoneNumber, message)
        updateChatList(receiverPhoneNumber, senderPhoneNumber, message)
    }

    // 👂 Listen to messages between two users
    fun listenForConversation(
        currentUser: String,
        otherUser: String,
        onNewMessage: (Message) -> Unit
    ) {
        val databaseReference = FirebaseDatabase.getInstance().reference

        val messageRef = databaseReference.child("messages")
            .child(currentUser)
            .child(otherUser)

        messageRef.orderByChild("timeStamp")
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    snapshot.getValue(Message::class.java)?.let { onNewMessage(it) }
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
                override fun onChildRemoved(snapshot: DataSnapshot) {}
                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
                override fun onCancelled(error: DatabaseError) {
                    Log.e("BaseViewModel", "Failed to load messages: ${error.message}")
                }
            })
    }

    // 📌 Update chat list with last message
    private fun updateChatList(
        ownerPhone: String,
        otherPhone: String,
        lastMessage: Message
    ) {
        val chatRef = FirebaseDatabase.getInstance().reference
            .child("chats")
            .child(ownerPhone)
            .child(otherPhone)

        val chat = ChatListModel(
            userId = otherPhone, // store phone or Firebase UID depending on your setup
            phoneNumber = otherPhone,
            name = "", // ideally fetched once from user profile
            profileImageUrl = "",
            lastMessage = lastMessage.message,
            lastMessageTime = lastMessage.timeStamp,
            unreadCount = 0
        )

        chatRef.setValue(chat)
    }

    // 📋 Load chat list for user
    fun loadChatData(callback: (List<ChatListModel>) -> Unit) {
        val currentUserPhone = FirebaseAuth.getInstance().currentUser?.phoneNumber ?: return
        val chatRef = FirebaseDatabase.getInstance().getReference("chats").child(currentUserPhone)

        chatRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val chatList = mutableListOf<ChatListModel>()
                for (childSnapshot in snapshot.children) {
                    childSnapshot.getValue(ChatListModel::class.java)?.let { chatList.add(it) }
                }
                callback(chatList.sortedByDescending { it.lastMessageTime })
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("BaseViewModel", "Failed to load chat list: ${error.message}")
                callback(emptyList())
            }
        })
    }
    fun addUserToChatList(phoneNumber: String, callback: (Boolean) -> Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser ?: run {
            callback(false)
            return
        }

        val usersRef = FirebaseDatabase.getInstance().getReference("users")
        usersRef.orderByChild("phoneNumber").equalTo(phoneNumber)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.children.firstOrNull()?.getValue(ChatListModel::class.java)
                    if (user != null) {
                        // Add to current user's chat list
                        val chatRef = FirebaseDatabase.getInstance().getReference("chats")
                            .child(currentUser.phoneNumber ?: "")
                            .child(phoneNumber)

                        val chat = ChatListModel(
                            userId = user.userId.takeIf { !it.isNullOrBlank() } ?: user.phoneNumber ?: "",
                            phoneNumber = user.phoneNumber ?: "",
                            name = user.name ?: "",
                            profileImageUrl = user.profileImageUrl ?: "",
                            lastMessage = "",
                            lastMessageTime = System.currentTimeMillis(),
                            unreadCount = 0
                        )

                        chatRef.setValue(chat)
                        // Update local StateFlow for immediate UI update
                        _chatList.value = _chatList.value + chat
                        callback(true)
                    } else {
                        callback(false)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(false)
                }
            })
    }

}