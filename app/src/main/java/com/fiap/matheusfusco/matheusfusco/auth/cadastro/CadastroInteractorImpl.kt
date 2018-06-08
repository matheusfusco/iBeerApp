package com.fiap.matheusfusco.matheusfusco.auth.cadastro

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

class CadastroInteractorImpl : BaseInteractorImpl(), CadastroInteractor {

    override fun register(context: Context, request: UserResponse?, listener: OnCadastroFinishedListener) {

        val api = createAPI(Constants.DOMAIN_URL, UserAPI::class.java) as UserAPI
        val call = api.register(request!!)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.code() == ServerStatusEnum.STATUS_OK.code || response.code() == ServerStatusEnum.STATUS_CREATED.code) {

                    listener.onRegisterSuccess()

                    return
                }

                if (response.code() == ServerStatusEnum.STATUS_FORBIDDEN.code || response.code() == ServerStatusEnum.STATUS_NOT_FOUND.code) {
                    listener.onRegisterError(R.string.not_found_error.toString())
                }

                if (response.code() == ServerStatusEnum.STATUS_UNAUTHORIZED.code) {
                    listener.onRegisterError(R.string.not_authorized_error.toString())
                    return
                }

                listener.onRegisterError(R.string.unknown_error.toString())
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                if (t.message != null)
                    listener.onRegisterError(t.message.toString())
                else
                    listener.onRegisterError("")
            }
        })
    }
}


interface CadastroInteractor {

    fun register(context: Context, request: UserResponse?, listener: OnCadastroFinishedListener)

}