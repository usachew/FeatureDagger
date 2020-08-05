package com.usacheow.featureauth.data

import com.usacheow.featureauth.data.models.AccessToken
import io.reactivex.Completable
import io.reactivex.Single

interface AuthRepository {

    fun signInWithLoginAndPassword(login: String, password: String): Single<AccessToken>

    fun signUpWithLoginAndPassword(login: String, password: String): Single<AccessToken>

    fun signInWithPhone(phone: String): Completable

    fun resendCode(phone: String): Completable

    fun verifyPhone(phone: String, code: String): Single<AccessToken>
}