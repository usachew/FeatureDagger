package com.usacheow.featurehello.presentation

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.utils.view.populate
import com.usacheow.coreui.utils.view.setListenerIfNeed
import com.usacheow.featurehello.R
import kotlinx.android.synthetic.main.view_banner.view.*

class BannerItemView
@JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr), Populatable<BannerItem> {

    override fun populate(model: BannerItem) {
        smallInfoHeaderView.populate(model.header)
        smallInfoValueView.text = model.value

        smallInfoClickableView.setListenerIfNeed(model.clickAction)
    }
}

data class BannerItem(
        val header: String? = null,
        val value: String,
        val clickAction: (() -> Unit)? = null
) : ViewType(R.layout.view_banner)