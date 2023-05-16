package com.android.example.androidarch.view

import android.content.Context
import androidx.appcompat.widget.AppCompatButton
import com.android.example.androidarch.IContract

/**
 * Count button
 *
 * @author 高超（gaochao.cc）
 * @since 2021/7/18
 */
class CountButton(
    context: Context,
) : AppCompatButton(context), IContract.ICountView {
    override fun bindPresenter(presenter: IContract.ICountPresenter) {
        setOnClickListener {
            presenter.requestNewCount()
        }
    }

    override fun showNewCount(count: Int) {
        text = "Click $count times!"
    }
}