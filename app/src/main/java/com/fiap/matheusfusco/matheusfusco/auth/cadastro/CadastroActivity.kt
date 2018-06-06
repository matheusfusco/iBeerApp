package com.fiap.matheusfusco.matheusfusco.auth.cadastro

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fiap.matheusfusco.matheusfusco.R
import com.fiap.matheusfusco.matheusfusco.auth.User
import com.fiap.matheusfusco.matheusfusco.auth.UserDao
import com.fiap.matheusfusco.matheusfusco.auth.UserDatabase
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val database = Room.databaseBuilder(
                this,
                UserDatabase::class.java,
                "techstore-database")
                .allowMainThreadQueries()
                .build()
        userDao = database.userDao()


        bt_register.setOnClickListener {
            saveUser()
            finish()
        }
    }

    private fun saveUser() {
        val createdUser = create()
        createdUser.let {
            userDao.add(it!!)
        }

    }

    private fun create(): User? {
        var user = userDao.verifyIfUserExists(et_email.text.toString())
        if (user == null) {
            val name = et_email.text.toString()
            val senha = et_password.text.toString()
            return User(null, name, senha)
        }
        else {
            Toast.makeText(this, "Esse e-mail já foi utilizado!", Toast.LENGTH_LONG).show()
            return null
        }
    }
}
