package com.usacheow.appdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.usacheow.coredata.database.Storage
import com.usacheow.coredata.database.UiMode
import com.usacheow.coreui.R
import com.usacheow.coreui.activity.SimpleActivity
import com.usacheow.coreui.base.Container
import com.usacheow.coreui.databinding.FragmentContainerBinding
import com.usacheow.coreui.delegate.ContainerDelegate
import com.usacheow.coreui.utils.view.enableLightMode
import com.usacheow.coreui.utils.view.enableNightMode
import com.usacheow.coreui.utils.view.enableSystemMode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoActivity : SimpleActivity<FragmentContainerBinding>(), Container {

    override val params = Params(
        viewBindingProvider = FragmentContainerBinding::inflate,
    )

    private val containerDelegate by lazy { ContainerDelegate(javaClass.simpleName) }

    override fun onCreate(savedInstanceState: Bundle?) {
        when (Storage(this).uiMode) {
            UiMode.LIGHT -> enableLightMode()
            UiMode.NIGHT -> enableNightMode()
            UiMode.SYSTEM -> enableSystemMode()
        }

        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        if (supportFragmentManager.backStackEntryCount == 0) {
            navigateTo(DemoContainerFragment.newInstance(), needAddToBackStack = false, needAnimate = false)
        }
    }

    override fun navigateTo(fragment: Fragment, needAddToBackStack: Boolean, needAnimate: Boolean) {
        containerDelegate.showFragment(supportFragmentManager, fragment, needAddToBackStack, needAnimate)
    }

    override fun resetContainer() {
        containerDelegate.resetContainer(supportFragmentManager)
    }

    override fun closeContainer() {
        finish()
    }

    override fun onBackPressed() {
        if (!containerDelegate.onBackPressed(supportFragmentManager)) {
            finish()
        }
    }
}