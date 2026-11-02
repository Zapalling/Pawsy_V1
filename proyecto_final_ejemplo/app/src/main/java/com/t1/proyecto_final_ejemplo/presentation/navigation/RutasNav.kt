package com.t1.proyecto_final_ejemplo.presentation.navigation

object RutasNav {
    const val INICIO = "inicio"
    const val FORMULARIO = "form?id={id}"
    const val BUSCAR = "buscar"
    const val DETALLE = "detalle/{id}"
    fun getTitulo(ruta: String?): String{
        return when{
            ruta == INICIO -> "Clínica VetCare"
            ruta?.startsWith("form") == true -> "Formulario Mascota"
            ruta == BUSCAR -> "Buscar Mascota"
            ruta?.startsWith("detalle") == true -> "Detalle de la Mascota"
            else -> "Clínica VetCare"
        }
    }
}