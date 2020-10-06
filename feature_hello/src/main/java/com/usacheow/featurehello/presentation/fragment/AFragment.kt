package com.usacheow.featurehello.presentation.fragment

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.listitem.ActionItem
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.toPx
import com.usacheow.featurehello.R
import com.usacheow.featurehello.presentation.BannerItem
import com.usacheow.featurehello.presentation.viewmodels.AViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_a.*

@AndroidEntryPoint
class AFragment : SimpleFragment() {

    private val viewModel by viewModels<AViewModel>({ requireParentFragment() })

    override val layoutId = R.layout.fragment_a
    override var needWhiteIcons = true

    companion object {
        fun newInstance() = AFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        headerView.updatePadding(top = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top)
        headerSpaceView.updateLayoutParams<ViewGroup.LayoutParams> {
            height = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
        }
        backgroundView.updateLayoutParams<ViewGroup.LayoutParams> {
            height = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top + 400.toPx
        }

        listView.updatePadding(bottom = insets.systemWindowInsetBottom + 56.toPx)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
//        rootView.progress = 1f
        rootView.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                val scale = 1.0f - (p3 / 5f)
                bannerListView.scaleX = scale
                bannerListView.scaleY = scale
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {

            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

            }
        })

        bannerListView.isNestedScrollingEnabled = false
        bannerListView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        bannerListView.adapter = ViewTypesAdapter(listOf(
                BannerItem(header = "Header", value = "Value"),
                BannerItem(header = "Header", value = "Value"),
                BannerItem(header = "Header", value = "Value"),
                BannerItem(header = "Header", value = "Value")
        ))

        listView.layoutManager = LinearLayoutManager(context)
        listView.adapter = ViewTypesAdapter(listOf(
            ActionItem(title = "1 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "2 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "3 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "4 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "5 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "6 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "7 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "8 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "9 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "10 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "11 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "12 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "13 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "14 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "15 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "16 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "17 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "18 Go to next screen", onItemClicked = ::openNextScreen)
        ))
    }

    private fun openNextScreen() {
        viewModel.x++
        getContainer { show(BFragment()) }
    }
}