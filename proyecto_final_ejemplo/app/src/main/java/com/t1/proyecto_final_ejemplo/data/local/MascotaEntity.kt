package com.t1.proyecto_final_ejemplo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mascotas")
data class MascotaEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val nombre: String,
    val especie: String,
    val dueno: String,
    val edad: String,
    val precio: Double, // En Soles (S/.)
    val observacion: String,
    val fotoRes: Int,
    val estadoVacuna: String = "Al día"
)
