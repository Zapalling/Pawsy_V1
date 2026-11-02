package com.t1.proyecto_final_ejemplo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.t1.proyecto_final_ejemplo.presentation.common.AppScaffold
import com.t1.proyecto_final_ejemplo.presentation.navigation.AppNavigation
import com.t1.proyecto_final_ejemplo.presentation.screens.inicio.InicioScreen
import com.t1.proyecto_final_ejemplo.ui.theme.Proyecto_final_ejemploTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_final_ejemploTheme {
                val navHostController = rememberNavController()
                AppScaffold(navHostController) { paddingValues ->
                    Box(modifier = Modifier.fillMaxSize().padding(paddingValues)){
                        AppNavigation(navHostController)
                    }
                }
            }
        }
    }
}

