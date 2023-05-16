package com.android.example.androidarch

/**
 * 契约类，提供 view、model 交互协议
 *
 * @author 高超（gaochao.cc）
 * @since 2021/7/18
 */
interface IContract {
    interface ICountView {
        fun bindPresenter(presenter: ICountPresenter)
        fun showNewCount(count: Int)
    }

    interface ICountModel {
        fun getNewCount(): Int
    }

    interface ICountPresenter {
        fun bindView(view: ICountView)
        fun requestNewCount()
    }
}