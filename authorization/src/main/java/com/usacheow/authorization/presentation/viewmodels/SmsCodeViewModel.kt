package com.usacheow.authorization.presentation.viewmodels

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.usacheow.authorization.R
import com.usacheow.coreuikit.viewmodels.livedata.ActionLiveData
import com.usacheow.coreuikit.viewmodels.livedata.SimpleAction
import javax.inject.Inject

private const val EMPTY_MESSAGE = ""
private const val EMPTY_CODE = ""

class SmsCodeViewModel
@Inject constructor(
    private val resources: Resources
) : ViewModel() {

    private var currentCode = ""
    private var maxCodeLength = 0

    private val _inputtedCodeLiveData by lazy { MutableLiveData<String>() }
    val inputtedCode: LiveData<String> get() = _inputtedCodeLiveData

    private val _isLoadingStateLiveData by lazy { MutableLiveData<Boolean>() }
    val isLoadingState: LiveData<Boolean> get() = _isLoadingStateLiveData

    private val _closeScreenLiveData by lazy { ActionLiveData<SimpleAction>() }
    val closeScreen: LiveData<SimpleAction> get() = _closeScreenLiveData

    private val _showMessageLiveData by lazy { ActionLiveData<String>() }
    val showMessage: LiveData<String> get() = _showMessageLiveData

    private val _isResendButtonEnabledLiveData by lazy { ActionLiveData<Boolean>() }
    val isResendButtonEnabled: LiveData<Boolean> get() = _isResendButtonEnabledLiveData

    private val _resendButtonTextLiveData by lazy { ActionLiveData<String>() }
    val resendButtonText: LiveData<String> get() = _resendButtonTextLiveData

    private val _codeLiveData by lazy { ActionLiveData<String>() }
    val code: LiveData<String> get() = _codeLiveData

    private val _resendCodeLiveData by lazy { ActionLiveData<SimpleAction>() }
    val resendCode: LiveData<SimpleAction> get() = _resendCodeLiveData

    fun setupInitState(codeLength: Int) {
        currentCode = ""
        maxCodeLength = codeLength

        _inputtedCodeLiveData.value = EMPTY_CODE
        _isLoadingStateLiveData.value = false
    }

    fun onResendClicked() {
        _resendCodeLiveData.value = SimpleAction()

        _isResendButtonEnabledLiveData.value = false
        _resendButtonTextLiveData.value = resources.getString(R.string.sms_code_resend_timer)
//        _resendButtonTextLiveData.value = resources.getString(R.string.sms_code_resend)
    }

    fun onDigitAdded(digit: String) {
        if (currentCode.length >= maxCodeLength) return

        currentCode += digit
        _inputtedCodeLiveData.value = currentCode
        if (currentCode.length == maxCodeLength) {
            onCodeInputted()
        }
    }

    fun onDigitRemoved() {
        if (currentCode.isEmpty()) return

        currentCode = currentCode.dropLast(1)
        _inputtedCodeLiveData.value = currentCode
    }

    private fun onCodeInputted() {
        _showMessageLiveData.value = EMPTY_MESSAGE
        _isLoadingStateLiveData.value = true
        _codeLiveData.value = currentCode
    }

    fun changeState(message: String?) {
        if (message == null) {
            _closeScreenLiveData.value = SimpleAction()
            return
        }
        _isLoadingStateLiveData.value = false
        _showMessageLiveData.value = message
    }
}