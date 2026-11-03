package com.t1.proyecto_final_ejemplo.presentation.screens.formulario

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.t1.proyecto_final_ejemplo.R
import com.t1.proyecto_final_ejemplo.data.local.MascotaEntity
import com.t1.proyecto_final_ejemplo.presentation.viewmodel.MascotaViewModel
import java.util.UUID

@Composable
fun FormularioScreen(
    mascotaId: String? = null,
    onSaved: () -> Unit = {},
    viewModel: MascotaViewModel = hiltViewModel()
) {
    var nombre by remember { mutableStateOf("") }
    var dueno by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var especie by remember { mutableStateOf("Perro · Labrador") }
    var precio by remember { mutableStateOf("") }
    var observaciones by remember { mutableStateOf("") }

    LaunchedEffect(mascotaId) {
        if (mascotaId != null) {
            val existing = viewModel.getMascotaById(mascotaId)
            if (existing != null) {
                nombre = existing.nombre
                dueno = existing.dueno
                edad = existing.edad
                especie = existing.especie
                precio = existing.precio.toString()
                observaciones = existing.observacion
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = if (mascotaId == null) "Registrar Nueva Mascota" else "Editar Mascota",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF101140)
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre de la mascota") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = dueno,
            onValueChange = { dueno = it },
            label = { Text("Dueño (Responsable)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad (ej. 3 años)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = especie,
            onValueChange = { especie = it },
            label = { Text("Especie / Raza (ej. Perro · Labrador)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Costo / Tarifa en Soles (S/.)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = observaciones,
            onValueChange = { observaciones = it },
            label = { Text("Observaciones / Historial Clínico") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Button(
            onClick = {
                val precioDouble = precio.toDoubleOrNull() ?: 50.0
                val entity = MascotaEntity(
                    id = mascotaId ?: UUID.randomUUID().toString(),
                    nombre = nombre.ifBlank { "Sin nombre" },
                    especie = especie.ifBlank { "Perro" },
                    dueno = dueno.ifBlank { "Desconocido" },
                    edad = edad.ifBlank { "1 año" },
                    precio = precioDouble,
                    observacion = observaciones.ifBlank { "Sin observaciones registradas." },
                    fotoRes = R.drawable.mascota_max,
                    estadoVacuna = "Al día"
                )
                if (mascotaId == null) {
                    viewModel.insertMascota(entity, onSaved)
                } else {
                    viewModel.updateMascota(entity, onSaved)
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3823A8)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Guardar Registro", fontWeight = FontWeight.Bold)
        }
    }
}
