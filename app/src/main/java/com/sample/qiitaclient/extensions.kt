package com.sample.qiitaclient

import android.content.Context
import androidx.annotation.IdRes
import android.view.View
import android.widget.Toast

fun <T : View> View.bindView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById(id) as T
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}