package com.usacheow.featureauth.presentation.router

import com.usacheow.appshared.otp.SmsCodeModalFragment
import com.usacheow.coreui.base.Router
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.featureauth.presentation.fragment.SignUpFragment
import javax.inject.Inject

class AuthorizationRouter
@Inject constructor() : Router {

    fun openSignUpScreen(fragment: SimpleFragment<*>) {
        fragment.getContainer {
            navigateTo(SignUpFragment.newInstance())
        }
    }

    fun openConfirmScreen(fragment: SimpleFragment<*>, codeLength: Int) {
        SmsCodeModalFragment.newInstance(codeLength)
            .show(fragment.childFragmentManager, SmsCodeModalFragment::javaClass.name)
    }
}