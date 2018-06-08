package com.fiap.matheusfusco.matheusfusco.auth.login

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.fiap.matheusfusco.matheusfusco.R
import com.fiap.matheusfusco.matheusfusco.auth.cadastro.CadastroActivity
import com.fiap.matheusfusco.matheusfusco.base.BaseView
import com.fiap.matheusfusco.matheusfusco.base.BottomNavigationActivity
import com.fiap.matheusfusco.matheusfusco.webservice.user.UserResponse
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.properties.Delegates

class LoginActivity : AppCompatActivity(), LoginView {
    override fun invalidPass() {
        etPassword.setError(resources.getString(R.string.input_valid_password), null)
    }

    override fun invalidForm() {
        etEmail.setError(resources.getString(R.string.input_valid_email), null)
    }

    override fun onLoginSuccess(userResponse: UserResponse) {
        startActivity(Intent(this, BottomNavigationActivity::class.java))
    }

    override fun onLoginError(errorMessage: String?) {
        Toast.makeText(this, resources.getString(R.string.error_to_login), Toast.LENGTH_LONG).show()
    }

    override fun showProgress(type: BaseView.ProgressType) {
        onLoadingStart()
    }

    override fun hideProgress() {
        onLoadingFinish()
    }

    override fun onConnectionFailed() {
    }

    override fun onAuthError() {
    }

    var mPresenter: LoginPresenterImpl by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mPresenter = LoginPresenterImpl(this, this)

        btLogin.setOnClickListener {
            if (etEmail.text.toString().isEmpty() || etPassword.text.toString().isEmpty()) {
                Toast.makeText(this, resources.getString(R.string.fill_all_fields_error), Toast.LENGTH_LONG).show()
            }
            else {
                mPresenter.login(etEmail.text.toString(), etPassword.text.toString())
            }

        }

        btRegister.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }

    private var progressDialog: ProgressDialog? = null

    fun onLoadingStart() {
        onLoadingFinish()
        progressDialog = ProgressDialog(this@LoginActivity, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
        progressDialog!!.setMessage(resources.getString(R.string.loading_msg))
        progressDialog!!.setCancelable(false)

        try {
            progressDialog!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun onLoadingFinish() {

        if (progressDialog != null) {
            progressDialog!!.dismiss()
        }
    }
}
