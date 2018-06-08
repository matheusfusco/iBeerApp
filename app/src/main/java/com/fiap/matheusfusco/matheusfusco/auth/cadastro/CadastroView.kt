package com.fiap.matheusfusco.matheusfusco.auth.cadastro

import com.fiap.matheusfusco.matheusfusco.base.BaseView

interface CadastroView : BaseView {

    fun invalidPass()

    fun invalidConfirmPass()

    fun invalidEmail()

    fun invalidName()

    fun onRegisterSuccess()

    fun onRegisterError(errorMessage: String?)


}