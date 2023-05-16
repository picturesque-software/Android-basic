package com.android.example.androidarch

/**
 * ViewModel
 *
 * @author 高超（gaochao.cc）
 * @since 2021/7/18
 */
class CountViewModel(private val counter: Counter) : IContract.ICountViewModel {
    private var value = 0

    override fun requestNewCount() {
        counter.updateData(++value)
    }
}