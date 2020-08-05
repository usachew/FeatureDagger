package com.usacheow.demo

import android.os.Bundle
import android.text.TextWatcher
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.button.SectionButton
import com.usacheow.coreui.uikit.header.SimpleAppBarLayout
import com.usacheow.coreui.uikit.list.Filter
import com.usacheow.coreui.utils.ext.PaddingValue
import com.usacheow.coreui.utils.ext.addCurrencyFormatter
import kotlinx.android.synthetic.main.fragment_views.chipsLayout
import kotlinx.android.synthetic.main.fragment_views.header
import kotlinx.android.synthetic.main.fragment_views.sectionButton
import kotlinx.android.synthetic.main.fragment_views.viewAmountInput
import kotlinx.android.synthetic.main.fragment_views.viewsScrollView

class ViewsFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_views

    private var textWatcher: TextWatcher? = null

    companion object {
        fun newInstance() = ViewsFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        viewsScrollView.updatePadding(bottom = insets.systemWindowInsetBottom)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        (header as SimpleAppBarLayout).apply {
            setBackground(R.color.colorGreyCard)
            title = "Simple Toolbar"
        }

        (sectionButton as SectionButton).populate(listOf(
            "One",
            "Two",
            "Three"
        ))

        textWatcher = viewAmountInput.addCurrencyFormatter("50000.00")

        chipsLayout.populate(setOf(
            Filter(1, "Chip 1", true),
            Filter(2, "Chip 2", false),
            Filter(3, "Chip 3", false),
            Filter(4, "Chip 4", false),
            Filter(5, "Chip 5", false)
        )) { _, _ -> }
    }

    override fun clearViews() {
        viewAmountInput.removeTextChangedListener(textWatcher)
    }
}