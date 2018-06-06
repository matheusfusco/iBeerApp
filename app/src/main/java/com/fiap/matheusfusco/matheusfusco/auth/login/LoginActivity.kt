package com.fiap.matheusfusco.matheusfusco.auth.login

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fiap.matheusfusco.matheusfusco.R
import com.fiap.matheusfusco.matheusfusco.auth.User
import com.fiap.matheusfusco.matheusfusco.auth.UserDao
import com.fiap.matheusfusco.matheusfusco.auth.UserDatabase
import com.fiap.matheusfusco.matheusfusco.base.BottomNavigationActivity
import com.fiap.matheusfusco.matheusfusco.auth.cadastro.CadastroActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val database = Room.databaseBuilder(
                this,
                UserDatabase::class.java,
                "techstore-database")
                .allowMainThreadQueries()
                .build()
        userDao = database.userDao()

        btLogin.setOnClickListener {

            var user = userDao.verifyIfUserExists(etEmail.text.toString())
            if (user == null) {
                Toast.makeText(this, "Usuário não existe!", Toast.LENGTH_LONG).show()
            }
            else {
                if (user.senha.equals(etPassword.text.toString(), false)) {
                    val intent = Intent(this, BottomNavigationActivity::class.java)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(this, "Senha inválida!", Toast.LENGTH_LONG).show()
                }
            }
        }

        btRegister.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}
