package com.android.example.androidarch

/**
 * Model
 *
 * @author 高超（gaochao.cc）
 * @since 2021/7/18
 */
object ModelCountNumber : IContract.ICountModel {
    override fun getNewCount(): Counter {
        return Counter()
    }
}