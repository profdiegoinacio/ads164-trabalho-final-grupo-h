package com.example.myapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapp.viewmodel.ProblemaViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ConfirmationScreen(
    viewModel: ProblemaViewModel = viewModel(),
    navController: NavController
) {
    val ultimoProblema by viewModel.ultimoProblemaReportado.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0F2F7))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Seu reporte está em análise!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))

            ultimoProblema?.let { problema ->
                Text(
                    text = "Gravidade: ${problema.gravidade}",
                    fontSize = 18.sp,
                    color = Color(0xFF303F9F)
                )
                Text(
                    text = "Descrição: ${problema.descricao}",
                    fontSize = 18.sp,
                    color = Color(0xFF303F9F)
                )
                Text(
                    text = "Localização: ${problema.latitude}, ${problema.longitude}",
                    fontSize = 16.sp,
                    color = Color(0xFF303F9F)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = {
                navController.navigate("mainScreen") {
                    popUpTo("mainScreen") { inclusive = true }
                }
            }) {
                Text("Voltar para o Início", color = Color.White)
            }
        }
    }
}