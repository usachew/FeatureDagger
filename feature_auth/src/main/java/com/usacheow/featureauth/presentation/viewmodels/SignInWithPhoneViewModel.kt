package com.usacheow.featureauth.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.usacheow.coredata.CONFIRM_CODE_LENGTH
import com.usacheow.coredata.network.error.ErrorProcessorImpl
import com.usacheow.coredata.network.observer.SimpleCompletableObserver
import com.usacheow.coredata.network.setRequestThreads
import com.usacheow.coreuikit.utils.ext.normalizedPhoneNumber
import com.usacheow.coreuikit.viewmodels.NetworkRxViewModel
import com.usacheow.coreuikit.viewmodels.livedata.ActionLiveData
import com.usacheow.coreuikit.viewmodels.livedata.SimpleAction
import com.usacheow.featureauth.domain.AuthInteractor
import javax.inject.Inject

private const val EXPECTED_PHONE_NUMBER_LENGTH = 10

class SignInWithPhoneViewModel
@Inject constructor(
    errorProcessor: ErrorProcessorImpl,
    private val interactor: AuthInteractor
) : NetworkRxViewModel(errorProcessor) {

    val codeConfirmMessage: LiveData<String> get() = _codeConfirmMessageLiveData
    private val _codeConfirmMessageLiveData by lazy { MutableLiveData<String>() }

    val submitButtonEnabled: LiveData<Boolean> get() = _submitButtonEnabledLiveData
    private val _submitButtonEnabledLiveData by lazy { MutableLiveData<Boolean>() }

    val closeScreen: LiveData<SimpleAction> get() = _closeScreenLiveData
    private val _closeScreenLiveData by lazy { ActionLiveData<SimpleAction>() }

    val openSignUpScreen: LiveData<SimpleAction> get() = _openSignUpScreenLiveData
    private val _openSignUpScreenLiveData by lazy { ActionLiveData<SimpleAction>() }

    val openConfirmScreen: LiveData<Int> get() = _openConfirmScreenLiveData
    private val _openConfirmScreenLiveData by lazy { ActionLiveData<Int>() }

    private var phoneNumber = ""

    init {
        _isLoadingStateLiveData.value = false
        _submitButtonEnabledLiveData.value = false
    }

    fun onPhoneChanged(phone: String) {
        _submitButtonEnabledLiveData.value = isValidPhoneNumber(phone.normalizedPhoneNumber())
    }

    private fun isValidPhoneNumber(phone: String) = phone.length == EXPECTED_PHONE_NUMBER_LENGTH

    fun onSubmitClicked(phone: String) {
        sendPhoneNumberIfValid(phone)
    }

    fun onSignInClicked(phone: String) {
        sendPhoneNumberIfValid(phone)
    }

    private fun sendPhoneNumberIfValid(phone: String) {
        phoneNumber = phone.normalizedPhoneNumber()
        if (!isValidPhoneNumber(phoneNumber)) return

        val observer = SimpleCompletableObserver.Builder()
            .onSubscribe { _isLoadingStateLiveData.postValue(true) }
            .onError(::onError)
            .onSuccess {
                _isLoadingStateLiveData.value = false
                _openConfirmScreenLiveData.value = CONFIRM_CODE_LENGTH
            }
            .build()

        disposables.clear()
        interactor.signInWithPhone(phone)
            .setRequestThreads()
            .subscribe(observer)
    }

    fun onSignUpClicked() {
        _openSignUpScreenLiveData.value = SimpleAction()
    }

    fun onCodeInputted(code: String) {
        if (code.isEmpty()) return

        val observer = SimpleCompletableObserver.Builder()
            .onError { _codeConfirmMessageLiveData.value = "Неверный код" }
            .onSuccess { _closeScreenLiveData.value = SimpleAction() }
            .build()
        interactor.verifyPhone(phoneNumber, code)
            .setRequestThreads()
            .subscribe(observer)
    }
}