package com.android.example.androidarch.model

import com.android.example.androidarch.IContract

/**
 * 计数器
 *
 * @author 高超（gaochao.cc）
 * @since 2021/7/18
 */
object ModelCountNumber : IContract.ICountModel {
    private var count = 0

    override fun getNewCount(): Int {
        return count++
    }
}