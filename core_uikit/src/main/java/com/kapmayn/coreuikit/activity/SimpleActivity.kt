package com.kapmayn.coreuikit.activity

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.kapmayn.core.DiApp
import com.kapmayn.core.analytics.AnalyticsTrackerHolder
import com.kapmayn.core.analytics.Events
import com.kapmayn.core.provider.DiProvider

abstract class SimpleActivity : AppCompatActivity() {

    protected abstract val layoutId: Int
    protected open var needTransparentBars = false

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject((application as DiApp).diProvider)

        if (needTransparentBars) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        setContentView(layoutId)

        setupViews(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        AnalyticsTrackerHolder.getInstance().trackEvent("onStart = ${this::class.java.simpleName}", Events.APP)
    }

    override fun onStop() {
        AnalyticsTrackerHolder.getInstance().trackEvent("onStop = ${this::class.java.simpleName}", Events.APP)
        super.onStop()
    }

    abstract fun inject(diProvider: DiProvider)

    protected open fun setupViews(savedInstanceState: Bundle?) {}
}