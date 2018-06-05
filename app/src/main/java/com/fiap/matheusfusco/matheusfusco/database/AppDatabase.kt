package com.fiap.matheusfusco.matheusfusco.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.fiap.matheusfusco.matheusfusco.model.Bar
import com.fiap.matheusfusco.matheusfusco.dao.BarDao


@Database(entities = [Bar::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun barDao(): BarDao
}