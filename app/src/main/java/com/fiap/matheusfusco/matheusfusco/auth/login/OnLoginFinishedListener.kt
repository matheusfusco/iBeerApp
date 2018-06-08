package com.fiap.matheusfusco.matheusfusco.auth.login

import com.fiap.matheusfusco.matheusfusco.webservice.user.UserResponse

interface OnLoginFinishedListener {

    fun onLoginSuccess(userResponse: UserResponse)

    fun onLoginError(errorMessage: String?)

}
