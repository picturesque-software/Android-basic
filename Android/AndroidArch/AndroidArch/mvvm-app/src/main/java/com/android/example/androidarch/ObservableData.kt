package com.android.example.androidarch

/**
 * 可观察的数据
 *
 * @author 高超（gaochao.cc）
 * @since 2021/7/18
 */
interface ObservableData<T : Any> {
    fun updateData(data: T)

    fun observe(observer: DataObserver<T>)
}