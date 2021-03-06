package com.usacheow.featureonboarding

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.featureonboarding.databinding.OnBoardingItemViewBinding

class OnBoardingItemView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null)
    : LinearLayout(context, attrs), Populatable<OnBoardingItem> {

    override fun populate(model: OnBoardingItem) {
        val binding = OnBoardingItemViewBinding.bind(this)

        binding.onboardingItemImageView.setImageResource(model.imageId)
        binding.onboardingItemTitleView.text = resources.getString(model.titleId)
        binding.onboardingItemDescriptionView.text = resources.getString(model.descriptionId)
    }
}

data class OnBoardingItem(
    @DrawableRes val imageId: Int,
    @StringRes val titleId: Int,
    @StringRes val descriptionId: Int
) : ViewType(R.layout.on_boarding_item_view)