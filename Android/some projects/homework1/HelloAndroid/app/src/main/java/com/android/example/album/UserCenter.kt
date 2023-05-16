package com.android.example.album

/**
 * 在内存中记录用户名和密码，仅做演示，实际项目需要持久化存储
 */
object UserCenter {

    // user email -> user obj
    private val users = mutableMapOf<String, User>()

    var signInUser: User? = null

    fun addUser(user: User) {
        users[user.email] = user
    }

    fun getUserByEmail(email: String): User? {
        return users[email]
    }
}