package com.fiap.matheusfusco.matheusfusco.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.fiap.matheusfusco.matheusfusco.model.Bar

@Dao
interface BarDao {

    @Query("SELECT * FROM bar")
    fun all(): List<Bar>

    @Insert
    fun add(vararg bar: Bar)


    @Delete
    fun delete(vararg bar: Bar): Int
}