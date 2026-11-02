package com.t1.proyecto_final_ejemplo.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CuadroInfo(icono: ImageVector, texto: String){
    val colorFondo = Color(0xFFCCEAD7)
    val colorBorde = Color(0xFFC7C3FF)
    val colorPrimario = Color(0xFF322A89)

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = colorFondo,
        border = BorderStroke(1.dp,colorBorde)

    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icono, contentDescription = "", tint = colorPrimario, modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = texto, color = colorPrimario, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }
    }
}