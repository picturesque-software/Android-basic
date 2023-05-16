package com.android.example.androidarch

/**
 * 契约类
 *
 * @author 高超（gaochao.cc）
 * @since 2021/7/18
 */
interface IContract {
    interface ICountViewModel {
        fun requestNewCount()
    }

    interface ICountModel {
        fun getNewCount(): Counter
    }
}