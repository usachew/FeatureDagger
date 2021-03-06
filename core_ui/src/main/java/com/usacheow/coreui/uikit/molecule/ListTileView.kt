package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.updatePadding
import com.facebook.shimmer.ShimmerFrameLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewListTileBinding
import com.usacheow.coreui.utils.*
import com.usacheow.coreui.utils.view.*

private const val ICON_PADDING_DP = 4
private const val DEFAULT_PADDING_DP = 0

class ListTileView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Populatable<ListTileItem> {

    private val binding by lazy { ViewListTileBinding.bind(this) }

    override fun populate(model: ListTileItem) {
        binding.valueView.populate(model.value)
        binding.topDescriptionView.populate(model.topDescription)
        binding.bottomDescriptionView.populate(model.bottomDescription)
        binding.leftIconView.apply {
            apply(model.leftImageInfo)
            updatePadding(model.leftImageInfo)
        }
        binding.rightIconView.apply {
            apply(model.rightImageInfo)
            updatePadding(model.rightImageInfo)
        }
        setListenerIfNeed(model.clickListener)
    }

    private fun ImageView.updatePadding(imageInfo: ImageInfo) {
        val padding = when (imageInfo) {
            is IconInfo -> ICON_PADDING_DP
            else -> DEFAULT_PADDING_DP
        }.toPx
        updatePadding(padding, padding, padding, padding)
    }
}

data class ListTileItem(
    val leftImageInfo: ImageInfo = EmptyInfo(),
    val rightImageInfo: ImageInfo = EmptyInfo(),
    val value: TextSource,
    val topDescription: TextSource? = null,
    val bottomDescription: TextSource? = null,
    val clickListener: (() -> Unit)? = null,
) : ViewType(R.layout.view_list_tile) {

    companion object {
        fun shimmer() = ShimmerTileItem(bottomLine = false, rightIcon = false)
    }
}