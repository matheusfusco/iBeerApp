package com.fiap.matheusfusco.matheusfusco.bar

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Bar::class], version = 2, exportSchema = false)
abstract class BarDatabase : RoomDatabase() {
    abstract fun barDao(): BarDao
}