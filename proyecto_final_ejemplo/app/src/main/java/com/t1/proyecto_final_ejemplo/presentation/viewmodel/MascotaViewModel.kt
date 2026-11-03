package com.t1.proyecto_final_ejemplo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.t1.proyecto_final_ejemplo.data.local.MascotaEntity
import com.t1.proyecto_final_ejemplo.data.repository.MascotaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MascotaViewModel @Inject constructor(
    private val repository: MascotaRepository
) : ViewModel() {

    val mascotas: StateFlow<List<MascotaEntity>> = repository.allMascotas
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch {
            repository.seedInitialDataIfNeeded()
        }
    }

    fun searchMascotas(query: String): StateFlow<List<MascotaEntity>> {
        return repository.searchMascotas(query)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }

    fun insertMascota(mascota: MascotaEntity, onComplete: () -> Unit = {}) {
        viewModelScope.launch {
            repository.insertMascota(mascota)
            onComplete()
        }
    }

    fun updateMascota(mascota: MascotaEntity, onComplete: () -> Unit = {}) {
        viewModelScope.launch {
            repository.updateMascota(mascota)
            onComplete()
        }
    }

    fun deleteMascota(mascota: MascotaEntity, onComplete: () -> Unit = {}) {
        viewModelScope.launch {
            repository.deleteMascota(mascota)
            onComplete()
        }
    }

    suspend fun getMascotaById(id: String): MascotaEntity? {
        return repository.getMascotaById(id)
    }
}
