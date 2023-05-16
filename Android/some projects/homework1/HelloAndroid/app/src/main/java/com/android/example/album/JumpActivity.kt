package com.android.example.album

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.example.helloandroid.R

/**
 * 相册的默认启动页面，会自动判断当前用户是否登陆，如果已经登陆就直接进入相册页面，否则进入登陆页面
 */
class JumpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (UserCenter.signInUser != null) {
            startActivity(Intent(this, AlbumActivity::class.java))
        } else {
            startActivity(Intent(this, SignInActivity::class.java))
        }
        // 就是一个跳转页面，没有必要留存
        finish()
    }
}