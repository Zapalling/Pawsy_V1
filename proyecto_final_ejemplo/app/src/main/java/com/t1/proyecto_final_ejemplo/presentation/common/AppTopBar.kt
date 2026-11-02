package com.t1.proyecto_final_ejemplo.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.t1.proyecto_final_ejemplo.presentation.navigation.BottomNavItem
import com.t1.proyecto_final_ejemplo.presentation.navigation.RutasNav


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(navController: NavHostController){
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val ruta = navBackStackEntry.value?.destination?.route
        val titulo = RutasNav.getTitulo(ruta)
        val mostrarBotonBack = ruta == RutasNav.DETALLE

        var menuAbierto by remember { mutableStateOf(false) }
        var mostrarAcercaDe by remember { mutableStateOf(false) }

        fun navegar(destino: String){
            navController.navigate(destino){
                popUpTo(navController.graph.findStartDestination().id){
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }


    Column{
            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 10.dp, top = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(mostrarBotonBack){
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "",
                            tint = Color(0xFF0F0069),
                            modifier = Modifier.size(28.dp))
                    }
                }else{
                    Icon(
                        imageVector =  Icons.Default.Pets,
                        contentDescription = "Logo",
                        tint = Color(0xFF0F0069),
                        modifier = Modifier.size(28.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = titulo,
                    color = Color(0xFF0F0069),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.Serif // Cambia según la fuente serif que uses
                )

                // Espaciador para empujar los botones de acción a la derecha
                Spacer(modifier = Modifier.weight(1f))

                // Botón de búsqueda
                IconButton(onClick = { navegar(BottomNavItem.Buscar.ruta) }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar",
                        tint = Color(0xFF0F0069)
                    )
                }

                // Menú de opciones
                Box {
                    IconButton(onClick = { menuAbierto = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Menú de opciones",
                            tint = Color(0xFF0F0069)
                        )
                    }
                    DropdownMenu(
                        expanded = menuAbierto,
                        onDismissRequest = { menuAbierto = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Inicio") },
                            leadingIcon = { Icon(Icons.Default.Home, contentDescription = null) },
                            onClick = {
                                menuAbierto = false
                                navegar(BottomNavItem.Inicio.ruta)
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Registrar mascota") },
                            leadingIcon = { Icon(Icons.Default.Add, contentDescription = null) },
                            onClick = {
                                menuAbierto = false
                                navegar(BottomNavItem.Form.passId())
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Buscar mascota") },
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                            onClick = {
                                menuAbierto = false
                                navegar(BottomNavItem.Buscar.ruta)
                            }
                        )
                        HorizontalDivider()
                        DropdownMenuItem(
                            text = { Text("Acerca de VetCare") },
                            leadingIcon = { Icon(Icons.Default.Info, contentDescription = null) },
                            onClick = {
                                menuAbierto = false
                                mostrarAcercaDe = true
                            }
                        )
                    }
                }
            }

            HorizontalDivider(thickness = 2.dp, color = Color(0xFFC8C4D5))

            if (mostrarAcercaDe) {
                AlertDialog(
                    onDismissRequest = { mostrarAcercaDe = false },
                    icon = { Icon(Icons.Default.Pets, contentDescription = null) },
                    title = { Text("VetCare") },
                    text = { Text("Aplicación para el registro de mascotas de una veterinaria. Versión 1.0") },
                    confirmButton = {
                        TextButton(onClick = { mostrarAcercaDe = false }) {
                            Text("Cerrar")
                        }
                    }
                )
            }
    }
}
