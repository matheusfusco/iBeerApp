package com.fiap.matheusfusco.matheusfusco.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fiap.matheusfusco.matheusfusco.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btLogin.setOnClickListener {
            val intent = Intent(this, BottomNavigationActivity::class.java)
            startActivity(intent)
        }

        btCadastrar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}
