package com.android.example.androidarch

/**
 * 计时数据类
 *
 * @author 高超（gaochao.cc）
 * @since 2021/7/18
 */
class Counter : ObservableData<Int> {
    private val observers = mutableListOf<DataObserver<Int>>()

    override fun updateData(data: Int) {
        observers.forEach { it.onDataUpdated(data) }
    }

    override fun observe(observer: DataObserver<Int>) {
        observers.add(observer)
    }
}