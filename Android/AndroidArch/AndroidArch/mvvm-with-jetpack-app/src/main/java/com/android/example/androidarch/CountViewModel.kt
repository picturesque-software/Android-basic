package com.android.example.androidarch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel
 *
 * @author 高超（gaochao.cc）
 * @since 2021/7/18
 */
class CountViewModel : ViewModel() {
    val count = MutableLiveData<Int>(0)
}