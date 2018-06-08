package com.fiap.matheusfusco.matheusfusco.auth.login

import com.fiap.matheusfusco.matheusfusco.base.BaseView
import com.fiap.matheusfusco.matheusfusco.webservice.user.UserResponse

interface LoginView : BaseView {
    fun invalidPass()

    fun invalidForm()

    fun onLoginSuccess(userResponse: UserResponse)

    fun onLoginError(errorMessage: String?)

}