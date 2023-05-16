package com.android.example.androidarch.presenter

import com.android.example.androidarch.IContract

/**
 * Presenter 层
 *
 * @author 高超（gaochao.cc）
 * @since 2021/7/18
 */
class CountPresenter(
    private val countModel: IContract.ICountModel
) : IContract.ICountPresenter {
    private var countView: IContract.ICountView? = null

    override fun bindView(view: IContract.ICountView) {
        countView = view
    }

    override fun requestNewCount() {
        countView?.showNewCount(countModel.getNewCount())
    }
}