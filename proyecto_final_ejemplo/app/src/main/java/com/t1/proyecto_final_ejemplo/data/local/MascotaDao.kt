package com.t1.proyecto_final_ejemplo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MascotaDao {
    @Query("SELECT * FROM mascotas")
    fun getAllMascotas(): Flow<List<MascotaEntity>>

    @Query("SELECT * FROM mascotas WHERE id = :id")
    suspend fun getMascotaById(id: String): MascotaEntity?

    @Query("SELECT * FROM mascotas WHERE nombre LIKE '%' || :query || '%' OR especie LIKE '%' || :query || '%' OR dueno LIKE '%' || :query || '%'")
    fun searchMascotas(query: String): Flow<List<MascotaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMascota(mascota: MascotaEntity)

    @Update
    suspend fun updateMascota(mascota: MascotaEntity)

    @Delete
    suspend fun deleteMascota(mascota: MascotaEntity)

    @Query("DELETE FROM mascotas WHERE id = :id")
    suspend fun deleteMascotaById(id: String)
}
