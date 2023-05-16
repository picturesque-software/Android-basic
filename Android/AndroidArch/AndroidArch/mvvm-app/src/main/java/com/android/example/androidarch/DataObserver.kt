package com.android.example.androidarch

/**
 * 数据观察接口
 *
 * @author 高超（gaochao.cc）
 * @since 2021/7/18
 */
interface DataObserver<T : Any> {
    fun onDataUpdated(newData: T)
}