package com.fiap.matheusfusco.matheusfusco.auth.cadastro

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.fiap.matheusfusco.matheusfusco.R
import com.fiap.matheusfusco.matheusfusco.base.BaseView
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlin.properties.Delegates

class CadastroActivity : AppCompatActivity(), CadastroView {
    override fun invalidPass() {
        et_password.setError(resources.getString(R.string.input_valid_password), null)
    }

    override fun invalidConfirmPass() {
    }

    override fun invalidEmail() {
        et_email.setError(resources.getString(R.string.input_valid_email), null)
    }

    override fun invalidName() {
        et_name.setError(resources.getString(R.string.input_valid_name), null)
    }

    override fun onRegisterSuccess() {
        Toast.makeText(this, resources.getString(R.string.register_success_msg), Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onRegisterError(errorMessage: String?) {
        Toast.makeText(this, resources.getString(R.string.register_error), Toast.LENGTH_LONG).show()
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

    var mPresenter: CadastroPresenterImpl by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        mPresenter = CadastroPresenterImpl(this, this)

        bt_register.setOnClickListener {
            mPresenter.register(et_email.text.toString(), et_password.text.toString(), et_password.text.toString(), et_name.text.toString())
        }
    }

    private var progressDialog: ProgressDialog? = null

    protected var alert: AlertDialog? = null

    fun onLoadingStart() {
        onLoadingFinish()
        progressDialog = ProgressDialog(this@CadastroActivity, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
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
