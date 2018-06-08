package com.fiap.matheusfusco.matheusfusco.auth.cadastro

interface OnCadastroFinishedListener {

    fun onRegisterSuccess()

    fun onRegisterError(errorMessage: String?)
}
