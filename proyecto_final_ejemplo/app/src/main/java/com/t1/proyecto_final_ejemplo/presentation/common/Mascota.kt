package com.t1.proyecto_final_ejemplo.presentation.common
import androidx.annotation.DrawableRes
import com.t1.proyecto_final_ejemplo.R

data class Mascota(
    val id: String,
    val nombre: String,
    val especie: String,
    val dueno: String,
    val edad: String,
    @DrawableRes val foto: Int
)

val mascotasInicio = listOf(
    Mascota("1", "Max", "Perro · Labrador", "Carlos Ramírez", "3 años", R.drawable.mascota_max),
    Mascota("2", "Luna", "Gato · Siamés", "María Torres", "2 años", R.drawable.mascota_luna),
    Mascota("3", "Rocky", "Perro · Bulldog", "Jorge Salazar", "5 años", R.drawable.mascota_rocky)
)

val mascotasBusqueda = listOf(
    Mascota("4", "Toby", "Perro · Beagle", "Ana Quispe", "4 años", R.drawable.mascota_toby),
    Mascota("5", "Mishi", "Gato · Persa", "Luis Paredes", "1 año", R.drawable.mascota_mishi)
)