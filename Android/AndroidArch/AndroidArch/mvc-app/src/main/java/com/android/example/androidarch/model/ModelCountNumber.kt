package com.android.example.androidarch.model

/**
 * 计数器 model
 *
 * @author 高超（gaochao.cc）
 * @since 2021/7/18
 */
object ModelCountNumber {
    private var count = 0

    fun getNewCount(): Int {
        return count++;
    }
}