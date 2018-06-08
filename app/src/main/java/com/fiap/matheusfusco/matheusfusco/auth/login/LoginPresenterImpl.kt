package com.fiap.matheusfusco.matheusfusco.auth.login

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import com.fiap.matheusfusco.matheusfusco.base.BaseView
import com.fiap.matheusfusco.matheusfusco.webservice.user.UserResponse
import kotlin.properties.Delegates

class LoginPresenterImpl : LoginPresenter, OnLoginFinishedListener {

    var context: Context by Delegates.notNull()
    var view: LoginView by Delegates.notNull()
    var interactor: LoginInteractor by Delegates.notNull()

    constructor(context: Context, view: LoginView) {
        this.view = view
        this.context = context
        this.interactor = LoginInteractorImpl()
    }

    override fun login(login: String?, pass: String?) {

        if (login == null || login.isEmpty() || !isValidEmail(login)) {
            view.invalidForm()
            return
        }

        if (pass == null || pass.isEmpty()) {
            view.invalidPass()
            return
        }

        view.showProgress(BaseView.ProgressType.PROGRESS_DIALOG)

        interactor.login(context, login, pass, this)
    }

    override fun onLoginSuccess(userResponse: UserResponse) {
        (context as Activity).runOnUiThread {
            view.hideProgress()
            view.onLoginSuccess(userResponse)
        }
    }

    override fun onLoginError(errorMessage: String?) {
        (context as Activity).runOnUiThread {
            view.hideProgress()
            view.onLoginError(errorMessage)
        }
    }

    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

}

interface LoginPresenter {
    fun login(login: String?, pass: String?)
}
