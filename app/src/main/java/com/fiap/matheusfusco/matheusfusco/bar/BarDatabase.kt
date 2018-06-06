package com.fiap.matheusfusco.matheusfusco.bar

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.fiap.matheusfusco.matheusfusco.bar.Bar
import com.fiap.matheusfusco.matheusfusco.bar.BarDao


@Database(entities = [Bar::class], version = 1, exportSchema = false)
abstract class BarDatabase : RoomDatabase() {
    abstract fun barDao(): BarDao
}