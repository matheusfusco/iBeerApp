package com.fiap.matheusfusco.matheusfusco.auth.cadastro

import android.app.Activity
import android.content.Context
import com.fiap.matheusfusco.matheusfusco.base.BaseView
import com.fiap.matheusfusco.matheusfusco.webservice.user.UserResponse
import kotlin.properties.Delegates

class CadastroPresenterImpl : CadastroPresenter, OnCadastroFinishedListener {

    var context: Context by Delegates.notNull()
    var view: CadastroView by Delegates.notNull()
    var interactor: CadastroInteractor by Delegates.notNull()

    constructor(context: Context, view: CadastroView) {
        this.view = view
        this.context = context
        this.interactor = CadastroInteractorImpl()
    }

    override fun register(email: String?, pass: String?, confirmPass: String?, name: String?) {

        if (email == null || email.isEmpty()) {
            view.invalidEmail()
            return
        }

        if (pass == null || pass.isEmpty()) {
            view.invalidPass()
            return
        }

        if (confirmPass == null || confirmPass.isEmpty() || !confirmPass.equals(pass, false)) {
            view.invalidConfirmPass()
        }

        if (name == null || name.isEmpty()) {
            view.invalidName()
            return
        }

        val request = UserResponse(name, email, email, pass, 1)

        view.showProgress(BaseView.ProgressType.PROGRESS_DIALOG)

        interactor.register(context, request, this)
    }

    override fun onRegisterSuccess() {
        (context as Activity).runOnUiThread {
            view.hideProgress()
            view.onRegisterSuccess()
        }
    }

    override fun onRegisterError(errorMessage: String?) {
        (context as Activity).runOnUiThread {
            view.hideProgress()
            view.onRegisterError(errorMessage)
        }
    }

}

interface CadastroPresenter {
    fun register(email: String?, pass: String?, confirmPass: String?, name: String?)
}
