package com.android.example.album

/**
 * 表示用户的数据类
 *
 */
data class User(
    val name: String,
    val email: String,
    val password: String,
    val age: Int,
    val gender: String,
    val address: String
)