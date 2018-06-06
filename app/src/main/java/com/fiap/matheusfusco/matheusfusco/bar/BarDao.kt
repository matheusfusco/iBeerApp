package com.fiap.matheusfusco.matheusfusco.bar

import android.arch.persistence.room.*
import com.fiap.matheusfusco.matheusfusco.bar.Bar

@Dao
interface BarDao {

    @Query("SELECT * FROM bar")
    fun all(): List<Bar>?

    @Query("SELECT * FROM bar WHERE id = :barID")
    fun findById(barID: Long): Bar

    @Update
    fun update(bar: Bar)

    @Insert
    fun add(vararg bar: Bar)

    @Delete
    fun delete(vararg bar: Bar): Int
}