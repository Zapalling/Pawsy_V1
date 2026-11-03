package com.t1.proyecto_final_ejemplo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MascotaEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mascotaDao(): MascotaDao
}
