package com.t1.proyecto_final_ejemplo.presentation.screens.inicio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.t1.proyecto_final_ejemplo.presentation.common.CuadroInfo
import com.t1.proyecto_final_ejemplo.presentation.common.MascotaItem
import com.t1.proyecto_final_ejemplo.presentation.viewmodel.MascotaViewModel

@Composable
fun InicioScreen(
    onDetalleMascota: (id: String) -> Unit,
    viewModel: MascotaViewModel = hiltViewModel()
) {
    val mascotas by viewModel.mascotas.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(top = 20.dp, bottom = 10.dp)) {
        Row(modifier = Modifier.fillMaxWidth().padding(end = 16.dp)) {
            Spacer(modifier = Modifier.weight(1f))
            CuadroInfo(Icons.Default.Pets, "Total: ${mascotas.size} pacientes registrados")
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(mascotas) { mascota ->
                MascotaItem(
                    mascota = mascota,
                    onDetalleClick = { onDetalleMascota(mascota.id) }
                )
            }
        }
    }
}
