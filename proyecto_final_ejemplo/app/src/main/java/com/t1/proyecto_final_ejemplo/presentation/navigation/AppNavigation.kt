package com.t1.proyecto_final_ejemplo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.t1.proyecto_final_ejemplo.presentation.screens.buscar.BuscarScreen
import com.t1.proyecto_final_ejemplo.presentation.screens.detalle.DetalleScreen
import com.t1.proyecto_final_ejemplo.presentation.screens.formulario.FormularioScreen
import com.t1.proyecto_final_ejemplo.presentation.screens.inicio.InicioScreen

@Composable
fun AppNavigation(navHostController: NavHostController){
    NavHost(
        navController = navHostController,
        startDestination = BottomNavItem.Inicio.ruta
    ){
        composable(BottomNavItem.Inicio.ruta){
            InicioScreen(
                onDetalleMascota = { id ->
                    navHostController.navigate(BottomNavItem.Detalle.passId(id))
                }
            )
        }
        composable(
            route = BottomNavItem.Form.ruta,
            arguments = listOf(navArgument("id"){
                type = NavType.StringType
                nullable = true
                defaultValue = null
            })
        ){ backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            FormularioScreen(
                mascotaId = id,
                onSaved = {
                    navHostController.popBackStack()
                }
            )
        }
        composable(BottomNavItem.Buscar.ruta) {
            BuscarScreen(
                onDetalleMascota = { id ->
                    navHostController.navigate(BottomNavItem.Detalle.passId(id))
                }
            )
        }
        composable(
            route = BottomNavItem.Detalle.ruta,
            arguments = listOf(navArgument("id"){type = NavType.StringType})
        ){ backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            DetalleScreen(
                mascotaId = id,
                onEditClick = { editId ->
                    navHostController.navigate(BottomNavItem.Form.passId(editId))
                },
                onDeleteComplete = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}
