package ru.geekbrain.android.myloginapp.domain.entities

data class UserProfile(
    val id: String,
    val login: String,
    val email:String,
    val avatarUrl: String,
    val pin: Int,
)
