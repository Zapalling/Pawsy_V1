package com.t1.proyecto_final_ejemplo.presentation.screens.detalle

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Label
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.t1.proyecto_final_ejemplo.data.local.MascotaEntity
import com.t1.proyecto_final_ejemplo.presentation.common.CuadroInfo
import com.t1.proyecto_final_ejemplo.presentation.common.InfoCard
import com.t1.proyecto_final_ejemplo.presentation.common.MetaBadge
import com.t1.proyecto_final_ejemplo.presentation.viewmodel.MascotaViewModel

@Composable
fun DetalleScreen(
    mascotaId: String,
    onEditClick: (String) -> Unit = {},
    onDeleteComplete: () -> Unit = {},
    viewModel: MascotaViewModel = hiltViewModel()
) {
    var mascota by remember { mutableStateOf<MascotaEntity?>(null) }

    LaunchedEffect(mascotaId) {
        mascota = viewModel.getMascotaById(mascotaId)
    }

    val currentMascota = mascota
    val colorAzulMedio = Color(0xFF3823A8)
    val colorAzulOscuro = Color(0xFF101140)
    val colorGris = Color(0xFF7D7F99)
    val colorGrisOscuro = Color(0xFF555B6E)
    val colorVerde = Color(0xFF00A86B)
    val colorRojo = Color(0xFFB3261E)
    val colorFondo = Color(0xFFF7F7FD)

    if (currentMascota == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Cargando información del paciente...", color = colorGris)
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorFondo)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Imagen de Portada con Sombra
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            modifier = Modifier.size(width = 180.dp, height = 240.dp)
        ) {
            Image(
                painter = painterResource(id = currentMascota.fotoRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 2. Chip 'PACIENTE FRECUENTE'
        CuadroInfo(Icons.Default.Pets, "PACIENTE FRECUENTE • S/. ${String.format("%.2f", currentMascota.precio)}")
        Spacer(modifier = Modifier.height(12.dp))

        // 3. Título y Dueño
        Text(
            text = currentMascota.nombre,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            color = colorAzulOscuro
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = null,
                tint = colorAzulMedio,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = currentMascota.dueno,
                fontSize = 14.sp,
                color = colorGrisOscuro,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 4. Grid de 2 Tarjetas (Edad y Especie)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            InfoCard(
                icon = Icons.Outlined.DateRange,
                label = "Edad",
                value = currentMascota.edad,
                modifier = Modifier.weight(1f)
            )
            InfoCard(
                icon = Icons.AutoMirrored.Outlined.Label,
                label = "Especie",
                value = currentMascota.especie,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 5. Barra de Disponibilidad / Vacunación
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFFF3F2FE),
            border = BorderStroke(1.dp, Color(0xFFE2E0FD)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(colorVerde, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Estado de vacunación",
                        fontSize = 11.sp,
                        color = colorGrisOscuro
                    )
                }
                Text(
                    text = currentMascota.estadoVacuna,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorAzulMedio
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 6. Sección Observaciones / Historial Clínico
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFEAEAF5)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.Menu,
                            contentDescription = null,
                            tint = colorAzulOscuro,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Observaciones",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorAzulOscuro
                        )
                    }
                    Text(
                        text = "HISTORIAL CLÍNICO",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorGris
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = currentMascota.observacion,
                    fontSize = 12.sp,
                    color = colorGris,
                    lineHeight = 18.sp,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    MetaBadge(text = "Tarifa: S/. ${String.format("%.2f", currentMascota.precio)}")
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 7. Botones de Acción (Editar / Eliminar)
        Button(
            onClick = { onEditClick(currentMascota.id) },
            colors = ButtonDefaults.buttonColors(containerColor = colorAzulMedio),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Editar Registro", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.deleteMascota(currentMascota) {
                    onDeleteComplete()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFDCDC)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = colorRojo,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Eliminar Registro", color = colorRojo, fontWeight = FontWeight.Bold)
        }
    }
}
