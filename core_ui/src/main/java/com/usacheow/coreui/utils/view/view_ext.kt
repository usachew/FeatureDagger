package com.usacheow.coreui.utils.view

import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding.view.RxView
import java.util.concurrent.TimeUnit

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun View.setVisible(isVisible: Boolean) {
    when {
        isVisible -> makeVisible()
        else -> makeGone()
    }
}

fun View.resize(widthPx: Int = width, heightPx: Int = height) {
    layoutParams?.let {
        it.width = widthPx
        it.height = heightPx
    }
}

fun View.updateMargins(leftPx: Int? = null, topPx: Int? = null, rightPx: Int? = null, bottomPx: Int? = null) {
    layoutParams = (layoutParams as ViewGroup.MarginLayoutParams).apply {
        setMargins(
            leftPx ?: leftMargin,
            topPx ?: topMargin,
            rightPx ?: rightMargin,
            bottomPx ?: bottomMargin
        )
    }
    requestLayout()
}

fun View.doOnClick(listener: () -> Unit) {
    RxView.clicks(this).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
        listener()
    }
}

fun View.setListenerIfNeed(listener: (() -> Unit)?) {
    isEnabled = if (listener == null) {
        setOnClickListener(null)
        false
    } else {
        doOnClick { listener.invoke() }
        true
    }
}