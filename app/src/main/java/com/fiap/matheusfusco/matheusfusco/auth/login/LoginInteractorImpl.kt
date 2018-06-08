package com.fiap.matheusfusco.matheusfusco.auth.login

import android.content.Context
import com.fiap.matheusfusco.matheusfusco.R
import com.fiap.matheusfusco.matheusfusco.base.BaseInteractorImpl
import com.fiap.matheusfusco.matheusfusco.webservice.Constants
import com.fiap.matheusfusco.matheusfusco.webservice.status.ServerStatusEnum
import com.fiap.matheusfusco.matheusfusco.webservice.user.UserAPI
import com.fiap.matheusfusco.matheusfusco.webservice.user.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginInteractorImpl : BaseInteractorImpl(), LoginInteractor {

    override fun login(context: Context, login: String, pass: String, listener: OnLoginFinishedListener) {

        val api = createAPI(Constants.DOMAIN_URL, UserAPI::class.java) as UserAPI
        val call = api.login(login, pass)

        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {

                if (response.code() == ServerStatusEnum.STATUS_OK.code || response.code() == ServerStatusEnum.STATUS_CREATED.code) {
                    listener.onLoginSuccess(response.body())
                    return
                }

                if (response.code() == ServerStatusEnum.STATUS_FORBIDDEN.code || response.code() == ServerStatusEnum.STATUS_NOT_FOUND.code) {
                    listener.onLoginError(context.resources.getString(R.string.not_found_error))
                }

                if (response.code() == ServerStatusEnum.STATUS_UNAUTHORIZED.code) {
                    listener.onLoginError(context.resources.getString(R.string.not_authorized_error))
                    return
                }

                listener.onLoginError(context.resources.getString(R.string.unknown_error))
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                if (t.message != null)
                    listener.onLoginError(t.message.toString())
                else
                    listener.onLoginError("")
            }
        })
    }
}


interface LoginInteractor {

    fun login(context: Context, login: String, pass: String, listener: OnLoginFinishedListener)

}