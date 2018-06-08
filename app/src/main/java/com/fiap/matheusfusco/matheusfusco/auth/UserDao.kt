package com.fiap.matheusfusco.matheusfusco.auth

import android.arch.persistence.room.*
import com.fiap.matheusfusco.matheusfusco.auth.User

@Dao
interface UserDao {

//    @Query("SELECT * FROM User WHERE email = :userEmail AND senha = :userPassword")
//    fun findUser(userEmail: String, userPassword: String): User

    @Query("SELECT * FROM User WHERE email = :userEmail")
    fun verifyIfUserExists(userEmail: String): User?

    @Insert
    fun add(vararg user: User)
}