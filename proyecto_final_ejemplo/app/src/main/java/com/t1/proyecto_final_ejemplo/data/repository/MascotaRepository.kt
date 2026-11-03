package com.t1.proyecto_final_ejemplo.data.repository

import com.t1.proyecto_final_ejemplo.R
import com.t1.proyecto_final_ejemplo.data.local.MascotaDao
import com.t1.proyecto_final_ejemplo.data.local.MascotaEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MascotaRepository @Inject constructor(
    private val mascotaDao: MascotaDao
) {
    val allMascotas: Flow<List<MascotaEntity>> = mascotaDao.getAllMascotas()

    fun searchMascotas(query: String): Flow<List<MascotaEntity>> {
        return mascotaDao.searchMascotas(query)
    }

    suspend fun getMascotaById(id: String): MascotaEntity? {
        return mascotaDao.getMascotaById(id)
    }

    suspend fun insertMascota(mascota: MascotaEntity) {
        mascotaDao.insertMascota(mascota)
    }

    suspend fun updateMascota(mascota: MascotaEntity) {
        mascotaDao.updateMascota(mascota)
    }

    suspend fun deleteMascota(mascota: MascotaEntity) {
        mascotaDao.deleteMascota(mascota)
    }

    suspend fun seedInitialDataIfNeeded() {
        // Can be called to prepopulate initial mock pets with prices in Soles (S/.)
        val initialList = listOf(
            MascotaEntity("1", "Max", "Perro · Labrador", "Carlos Ramírez", "3 años", 120.0, "Paciente en buen estado general. Mantiene su calendario de vacunación y desparasitación al día.", R.drawable.mascota_max, "Al día"),
            MascotaEntity("2", "Luna", "Gato · Siamés", "María Torres", "2 años", 90.0, "Control de peso y revisión dental favorable. Muy activa.", R.drawable.mascota_luna, "Al día"),
            MascotaEntity("3", "Rocky", "Perro · Bulldog", "Jorge Salazar", "5 años", 150.0, "Requiere control dermatológico semestral.", R.drawable.mascota_rocky, "Pendiente refuerzo"),
            MascotaEntity("4", "Toby", "Perro · Beagle", "Ana Quispe", "4 años", 110.0, "Examen físico completo realizado sin novedades.", R.drawable.mascota_toby, "Al día"),
            MascotaEntity("5", "Mishi", "Gato · Persa", "Luis Paredes", "1 año", 95.0, "Vacuna antirrábica aplicada correctamente.", R.drawable.mascota_mishi, "Al día")
        )
        // Insert if empty or ensure existence
        for (item in initialList) {
            val existing = mascotaDao.getMascotaById(item.id)
            if (existing == null) {
                mascotaDao.insertMascota(item)
            }
        }
    }
}
